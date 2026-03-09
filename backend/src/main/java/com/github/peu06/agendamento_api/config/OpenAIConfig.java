package com.github.peu06.agendamento_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI();
    }
}
