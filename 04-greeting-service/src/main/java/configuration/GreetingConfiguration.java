package configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("greeting-service") //anotacao que determinar a configuracao do servico
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GreetingConfiguration {

    private String greeting;
    private String defaultValue;
}
