package com.perfulandia.sucursales.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Sucursales - Perfulandia")
                        .version("1.0")
                        .description("Documentación de endpoints para gestionar sucursales del sistema Perfulandia"));
    }
}