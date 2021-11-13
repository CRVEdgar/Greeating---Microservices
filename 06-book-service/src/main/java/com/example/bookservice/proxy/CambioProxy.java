package com.example.bookservice.proxy;

import com.example.bookservice.response.Cambio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "cambio-service") //endpoint do cliente/microserviço a ser acessado
public interface CambioProxy {

    @GetMapping(value = "cambio-service/{amount}/{from}/{to}")
    public Cambio getCambio(@PathVariable("amount") Double amount, @PathVariable("from") String from,
                            @PathVariable("to") String to
    );
}
