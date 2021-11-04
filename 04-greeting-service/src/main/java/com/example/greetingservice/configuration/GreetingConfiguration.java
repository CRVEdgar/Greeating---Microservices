package com.example.greetingservice.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("greeting-service") //anotacao que determinar a configuracao do servico
@RefreshScope //atualiza as automaticamente apos a alteacao do servidor de configuracao
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GreetingConfiguration {

    private String greeting;
    private String defaultValue;
}
