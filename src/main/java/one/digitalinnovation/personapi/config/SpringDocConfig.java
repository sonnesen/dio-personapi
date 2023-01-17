package one.digitalinnovation.personapi.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SpringDocConfig {

    @Bean
    public OpenAPI apiInfo() {
        Server server = new Server().url("http://localhost:8080");
        return new OpenAPI()
                .info(new Info()
                        .title("Person API")
                        .description("Person API")
                        .version("0.0.2"))
                .servers(Arrays.asList(server));
    }
}
