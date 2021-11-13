package com.example.bookservice.controller;

import com.example.bookservice.model.Book;
import com.example.bookservice.proxy.CambioProxy;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.response.Cambio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping(value = "book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy; //injeção para acessar o serviço via FeignClient

    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency){
        var book = repository.findById(id).orElseThrow( () -> new RuntimeException("Livro nao encontrado"));

        var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

        var port =  environment.getProperty("local.server.port");

        book.setEnvironment("book port: " +port + " || Cambio Port: " + cambio.getEnviroment());
        book.setPrice(cambio.getConvertedValue());
        return book;
    }

    /*IMPLEMENTAÇÃO SEM FEIGN*/
//    @GetMapping(value = "/{id}/{currency}")
//    public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency){
//        var book = repository.findById(id).orElseThrow( () -> new RuntimeException("Livro nao encontrado"));
//
//        HashMap<String, String> params = new HashMap<>();
//
//        params.put("amount", book.getPrice().toString());
//        params.put("from", "USD");
//        params.put("to", currency);
//
//
//        var response = new RestTemplate()
//                .getForEntity("http://localhost:8000/cambio-service/" +
//                                "{amount}/{from}/{to}",
//                        Cambio.class, //classe que sera convertida a informação vinda do endpoint
//                        params);
//
//        var cambio = response.getBody();
//
//        var port =  environment.getProperty("local.server.port");
//
//        book.setEnvironment(port);
//        book.setPrice(cambio.getConvertedValue());
//        return book;
//    }
}
