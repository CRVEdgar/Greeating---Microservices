package com.example.bookservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Tag(name = "Foo Bar TAG")
@RestController
@RequestMapping(value = "book-service")
public class FooBarController {

    private Logger logger =  LoggerFactory.getLogger(FooBarController.class);

    @Operation(summary = "busca um livro pelo seu id")
    @GetMapping("/foo-bar")
    @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod") // name c/c instances:foo-bar: [.yml] // fallbackMethod = "fallbackMethod" c/c String fallbackMethod(Exception e)
    public String foobar(){
        logger.info("MESSAGE: Request for foo-bar is received!");
        var response = new RestTemplate()
                .getForEntity("http://localhost:8080/foo-bar", String.class);
        return response.getBody();
    }

    @GetMapping("/foo-bar/cctbrk")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod") // fallbackMethod = "fallbackMethod" c/c String fallbackMethod(Exception e)
    public String foobarCircuitBreak(){
        logger.info("MESSAGE: Request for foo-bar is received!");
        var response = new RestTemplate()
                .getForEntity("http://localhost:8080/foo-bar/cctbrk", String.class);
        return response.getBody();
    }

    @GetMapping("/foo-bar/rtlmt")
    @RateLimiter(name = "default")
    public String foobarReteLimiter(){
        logger.info("MESSAGE: Request for foo-bar is received! - WITH RateLimite");
        return "MESSAGE: Limitadas as requisições por periodo! - WITH RateLimite";
    }

    @GetMapping("/foo-bar/bkhd")
    @Bulkhead(name = "default")
    public String foobarBulkhead(){
        logger.info("MESSAGE: Request for foo-bar is received! - WITH Bulkhead");
        return "MESSAGE: Limitadas as requisições por periodo! - WITH Bulkhead";
    }

    private String fallbackMethod(Exception e){
        //metodo sera invocado apos a tentativa de N chamadas ao endpoint /foo-bar
        // captura a exception e executa this metodo
        return "MESSAGE: fallbackMethod foo-bar";
    }
}
