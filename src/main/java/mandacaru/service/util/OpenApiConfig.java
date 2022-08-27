package mandacaru.service.util;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@Configuration
@OpenAPIDefinition
@SecurityScheme(name = "oauth", type = SecuritySchemeType.HTTP, scheme = "bearer")
public class OpenApiConfig {

}

