package com.sprint05.integration.moviedb.model;

import com.sprint05.integration.moviedb.model.request.MoviedbIntegrationRequest;
import com.sprint05.integration.moviedb.model.response.MoviedbIntegrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class MoviedbIntegration {

    private WebClient webClient;

    public Mono<MoviedbIntegrationResponse> getMovie(MoviedbIntegrationRequest moviedbIntegrationRequest) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/om")
                        .queryParam("t", moviedbIntegrationRequest.getTitle())
                        .build())
                .retrieve()
                .bodyToMono(MoviedbIntegrationResponse.class);
    }
}
