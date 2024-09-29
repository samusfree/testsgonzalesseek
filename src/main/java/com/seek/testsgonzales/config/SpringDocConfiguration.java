package com.seek.testsgonzales.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
    name = "BearerAuthentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer",
    in = SecuritySchemeIn.HEADER,
    paramName = "Authorization"
)
public class SpringDocConfiguration {

  @Bean
  OpenAPI apiInfo() {
    return new OpenAPI().info(new Info().title("Candidates API")
        .description("Candidates API for Seek")
        .contact(new Contact().email("sagonzales89@gmail.com")).license(new License()
            .name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html"))
        .version("1.0.0"));
  }
}
