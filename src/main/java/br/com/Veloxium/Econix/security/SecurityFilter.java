package br.com.Veloxium.Econix.security;

import br.com.Veloxium.Econix.http.LoginFeing;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private LoginFeing feing;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT =  this.getToken(request);
        var list = feing.auth(tokenJWT);
        if(list.isEmpty())throw new RuntimeException("USER SEM PERMISSON");
        var granted = this.grand(list);
        var usetAuthentications = new UsernamePasswordAuthenticationToken(null, null, granted);
        SecurityContextHolder.getContext().setAuthentication(usetAuthentications);


        filterChain.doFilter(request,response);
    }

    private Collection<? extends GrantedAuthority> grand(List<String> crede) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for(String c:crede){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(c));
        }
        return simpleGrantedAuthorities;
    }

    private String getToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if(token != null){
            return token.replace("Bearer ","");
        }
        throw  new RuntimeException("ERRO TOKEN");
    }
}
