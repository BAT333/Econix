package br.com.Veloxium.Econix.http;
import org.springframework.stereotype.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient("restaurantes")
public interface deleteisi {
    @RequestMapping(method = RequestMethod.DELETE, value = "/drink/{id}")
    String delete(@PathVariable Long id);

}

