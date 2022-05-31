package com.sprint05.integration.config.webclient;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl("https://moviesdb5.p.rapidapi.com")
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .defaultHeader("x-rapidapi-host", "moviesdb5.p.rapidapi.com")
                .defaultHeader("x-rapidapi-key", "c313495212msh61074658381b602p118932jsn79f0a21a8167")
                .build();
    }
}
