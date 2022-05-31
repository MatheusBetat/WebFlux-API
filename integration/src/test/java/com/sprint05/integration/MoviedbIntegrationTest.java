package com.sprint05.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint05.integration.moviedb.model.MoviedbIntegration;
import com.sprint05.integration.moviedb.model.request.MoviedbIntegrationRequest;
import com.sprint05.integration.moviedb.model.response.MoviedbIntegrationResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = MoviedbIntegration.class)
class MoviedbIntegrationTest {

    @InjectMocks
    private MoviedbIntegration moviedbIntegration;

    private static ClientAndServer server;

    @BeforeEach
    public void startServer() {
        server = startClientAndServer();
    }

    @AfterEach
    public void stopServer() {
        server.close();
    }

    @BeforeEach
    void setupClass() {
        WebClient webClient = WebClient.builder()
                .baseUrl(String.format("http://localhost:%d", server.getPort()))
                .defaultHeaders(httpHeaders ->
                        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .build();

        moviedbIntegration = new MoviedbIntegration(webClient);
    }

    @Test
    void mustReturnMovie() throws JsonProcessingException {

        var moviedbIntegrationRequest = MoviedbIntegrationRequest.builder()
                .title("The Avengers")
                .build();


        var moviedbIntegrationResponse = MoviedbIntegrationResponse.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .plot("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        var objectMaper = new ObjectMapper();

        var responseBody = objectMaper.writeValueAsString(moviedbIntegrationResponse);

        HttpRequest request = HttpRequest.request()
                .withPath("/v1/moviedb")
                .withPath("/om")
                .withQueryStringParameter("t", "The Avengers")
                .withMethod("GET")
                .withHeaders(Header.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        HttpResponse response = HttpResponse.response(responseBody)
                .withStatusCode(HttpStatus.OK.value())
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        server.when(request).respond(response);

        StepVerifier.create(moviedbIntegration.getMovie(moviedbIntegrationRequest))
                .assertNext((MoviedbIntegrationResponse orderResponse) ->
                        Assertions.assertEquals("The Avengers", orderResponse.getTitle()))
                .verifyComplete();

    }
}
