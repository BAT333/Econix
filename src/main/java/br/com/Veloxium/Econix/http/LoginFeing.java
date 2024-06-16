package br.com.Veloxium.Econix.http;

import br.com.Veloxium.Econix.http.config.FeignConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "userSeguranca",configuration = FeignConfig.class)
public interface LoginFeing{

    @RequestMapping(method = RequestMethod.POST, value = "/login/auth")
    @CircuitBreaker(name = "erroLogin")
    List<String> auth(String token);

}
