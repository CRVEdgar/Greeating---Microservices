package com.example.cambioservice.controller;

import com.example.cambioservice.model.Cambio;
import com.example.cambioservice.repository.CambioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Tag(name = "Cambio Service API") //LOCAL DE ACESSO - localhost:8000/swagger-ui.html
@RestController
@RequestMapping("cambio-service")
public class CambioController {

    @Autowired
    private Environment environment;

    @Autowired
    private CambioRepository repository;

    @Operation(summary = "Obter cambio de variação de moeda")
    @GetMapping(value = "/{amount}/{from}/{to}")
    public Cambio getCambio(@PathVariable("amount") BigDecimal amount, @PathVariable("from") String from,
                            @PathVariable("to") String to
    ) {
        var cambio = repository.findByFromAndTo(from, to);
        if (cambio == null) throw new RuntimeException("Currency Unsupported");

        var port = environment.getProperty("local.server.port"); // recperando a porta da aplicação

        BigDecimal conversionFactor = cambio.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount);
        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING)); //arredonda o valor para apenas duas casas decimais
        cambio.setEnviroment(port);

        return cambio;
    }
}
