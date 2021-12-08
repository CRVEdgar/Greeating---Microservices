//package com.example.apigateway.configuration;
//
//import org.springframework.cloud.gateway.route.Route;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.Buildable;
//import org.springframework.cloud.gateway.route.builder.PredicateSpec;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.function.Function;
//
//@Configuration
//public class ApiGatewayConfiguration {
// TODO: rotas configuradas no yml
//
//    @Bean
//    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
//
//        return builder.routes()
//                .route(p -> p.path("/get")
//                        .filters(
//                                f -> f.addRequestHeader("NomeCabecalho", "Valor")
//                                        .addRequestParameter("Parametro", "Valor")
//                        )
//                        .uri("http://httpbin.org:80"))
//                // caminhos de acesso ao loadbalance do Eureka
//                .route(p -> p.path("/cambio-service/**")
//                        .uri("lb://cambio-service")) // nome do serviço registrado no Eureka
//                .route(p -> p.path("/book-service/**")
//                        .uri("lb://book-service")) // nome do serviço registrado no Eureka
//                .build();
//    }
//}
