package br.com.Veloxium.Econix.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public record DateAuthe(
        UserDetails user,
        Collection<? extends GrantedAuthority> authorities

) {

}
