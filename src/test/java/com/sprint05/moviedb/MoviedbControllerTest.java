package com.sprint05.moviedb;

import com.sprint05.contract.controller.v1.moviedb.MoviedbController;
import com.sprint05.contract.controller.v1.moviedb.facade.MoviedbControllerFacade;
import com.sprint05.contract.controller.v1.moviedb.model.request.MoviedbControllerRequest;
import com.sprint05.contract.controller.v1.moviedb.model.response.MoviedbControllerResponse;
import com.sprint05.impl.moviedb.facade.MoviedbServiceFacade;
import com.sprint05.impl.moviedb.model.request.MoviedbServiceRequest;
import com.sprint05.impl.moviedb.model.response.MoviedbServiceResponse;
import com.sprint05.impl.moviedb.service.MoviedbService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
@ContextConfiguration(classes = {MoviedbController.class, MoviedbControllerFacade.class,
        MoviedbServiceFacade.class})
class MoviedbControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    MoviedbService moviedbService;

    @Test
    void getMovie() {

        MoviedbServiceRequest moviedbServiceRequest = MoviedbServiceRequest.builder()
                .title("The Avengers")
                .build();

        MoviedbControllerRequest moviedbControllerRequest = MoviedbControllerRequest.builder()
                .title("The Avengers")
                .build();

        MoviedbControllerResponse moviedbControllerResponse = MoviedbControllerResponse.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .plot("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        MoviedbServiceResponse moviedbServiceResponse = MoviedbServiceResponse.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .plot("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        Mockito.when(moviedbService.getMovie(moviedbServiceRequest))
                .thenReturn(Mono.just(moviedbServiceResponse));

        webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/v1/moviedb")
                        .queryParam("moviedbControllerRequest", moviedbControllerRequest.getTitle())
                        .build())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(MoviedbControllerResponse.class)
                .isEqualTo(moviedbControllerResponse);
    }

    @Test
    void getMyMovie() {

        String movieId = "628656aa4202524fead3d32f";

        MoviedbControllerResponse moviedbControllerResponse = MoviedbControllerResponse.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .plot("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        MoviedbServiceResponse moviedbServiceResponse = MoviedbServiceResponse.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .plot("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        Mockito.when(moviedbService.getMyMovie(movieId))
                .thenReturn(Mono.just(moviedbServiceResponse));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/moviedb/")
                        .path(movieId)
                        .build())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(MoviedbControllerResponse.class)
                .isEqualTo(moviedbControllerResponse);
    }

    @Test
    void getAllMovies() {

        MoviedbControllerResponse moviedbControllerResponse = MoviedbControllerResponse.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .plot("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        MoviedbServiceResponse moviedbServiceResponse = MoviedbServiceResponse.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .plot("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        Mockito.when(moviedbService.getAll())
                .thenReturn(Flux.just(moviedbServiceResponse));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/moviedb/movies")
                        .build())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(MoviedbControllerResponse.class)
                .isEqualTo(List.of(moviedbControllerResponse));
    }

    @Test
    void getMovieByIds() {

        List<String> ids = List.of("628656aa4202524fead3d32f");

        MoviedbControllerResponse moviedbControllerResponse = MoviedbControllerResponse.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .plot("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        MoviedbServiceResponse moviedbServiceResponse = MoviedbServiceResponse.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .plot("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        Mockito.when(moviedbService.findOneOrMany(ids))
                .thenReturn(Flux.just(moviedbServiceResponse));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/moviedb/ids")
                        .queryParam("ids", ids)
                        .build())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(MoviedbControllerResponse.class)
                .isEqualTo(List.of(moviedbControllerResponse));
    }

    @Test
    void deleteMoviesByIds() {

        List<String> ids = List.of("628656aa4202524fead3d32f");

        Mockito.when(moviedbService.delete(ids))
                .thenReturn(Mono.empty());

        webTestClient.delete()
                .uri(uriBuilder -> uriBuilder.path("/v1/moviedb/delete")
                        .queryParam("ids", ids)
                        .build())
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

    }
}