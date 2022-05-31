package com.sprint05.moviedb;

import com.sprint05.impl.moviedb.model.request.MoviedbServiceRequest;
import com.sprint05.impl.moviedb.model.response.MoviedbServiceResponse;
import com.sprint05.impl.moviedb.service.MoviedbService;
import com.sprint05.impl.repository.MoviedbRepository;
import com.sprint05.impl.repository.entity.MoviedbEntity;
import com.sprint05.integration.moviedb.model.MoviedbIntegration;
import com.sprint05.integration.moviedb.model.request.MoviedbIntegrationRequest;
import com.sprint05.integration.moviedb.model.response.MoviedbIntegrationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebFluxTest(MoviedbService.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MoviedbService.class, MoviedbIntegration.class, MoviedbRepository.class})
class MoviedbServiceTest {

    @Autowired
    private MoviedbService moviedbService;

    @MockBean
    private MoviedbIntegration moviedbIntegration;

    @MockBean
    private MoviedbRepository moviedbRepository;

    @Test
    void getMovie(){

        MoviedbEntity entityRequest = MoviedbEntity.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .description("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        MoviedbEntity entityResponse = MoviedbEntity.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .description("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        MoviedbServiceRequest moviedbServiceRequest = MoviedbServiceRequest.builder()
                .title("The Avengers")
                .build();

        MoviedbIntegrationResponse moviedbIntegrationResponse = MoviedbIntegrationResponse.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .plot("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        MoviedbIntegrationRequest moviedbIntegrationRequest = MoviedbIntegrationRequest.builder()
                .title("The Avengers")
                .build();

        MoviedbServiceResponse resultExpected = MoviedbServiceResponse.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .plot("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();


       Mockito.when(moviedbIntegration.getMovie(moviedbIntegrationRequest))
                .thenReturn(Mono.just(moviedbIntegrationResponse));
        Mockito.when(moviedbRepository.save(entityRequest))
                .thenReturn(Mono.just(entityResponse));

       Mono<MoviedbServiceResponse> expect = moviedbService.getMovie(moviedbServiceRequest);


        StepVerifier.create(expect)
                .assertNext(response -> {
                        assertEquals(resultExpected, response);
                })
                .verifyComplete();


    }

    @Test
    void getMyMovie(){

        String movieId = "628656aa4202524fead3d32f";

        MoviedbEntity entity = MoviedbEntity.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .description("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        Mockito.when(moviedbRepository.findById(movieId)).thenReturn(Mono.just(entity));

        Mono<MoviedbServiceResponse> expect = this.moviedbService.getMyMovie(movieId);

        StepVerifier
                .create(expect)
                .consumeNextWith((MoviedbServiceResponse moviedbResponse) -> {
                    assertEquals(moviedbResponse.getMovieId(), entity.getMovieId());
                    assertEquals(moviedbResponse.getTitle(), entity.getTitle());
                    assertEquals(moviedbResponse.getMovieId(), movieId);
                })
                .verifyComplete();

    }

    @Test
    void getAll(){

        MoviedbEntity entity = MoviedbEntity.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .description("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        Mockito.when(moviedbRepository.findAll()).thenReturn(Flux.just(entity));

        Flux<MoviedbServiceResponse> expect = this.moviedbService.getAll();

        StepVerifier
                .create(expect)
                .consumeNextWith((MoviedbServiceResponse moviedbResponse) -> {
                    assertEquals(moviedbResponse.getMovieId(), entity.getMovieId());
                    assertEquals(moviedbResponse.getTitle(), entity.getTitle());
                })
                .verifyComplete();

    }

    @Test
    void findOneOrMany(){

        List<String> ids = List.of("628656aa4202524fead3d32f");

        MoviedbEntity entity = MoviedbEntity.builder()
                .movieId("628656aa4202524fead3d32f")
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year("2012")
                .description("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        Mockito.when(moviedbRepository.findAllById(ids))
                .thenReturn(Flux.just(entity));

        Flux<MoviedbServiceResponse> expect = this.moviedbService.findOneOrMany(ids);

        StepVerifier
                .create(expect)
                .consumeNextWith((MoviedbServiceResponse moviedbResponse) -> {
                    assertEquals(moviedbResponse.getMovieId(), entity.getMovieId());
                    assertEquals(moviedbResponse.getTitle(), entity.getTitle());

                })
                .verifyComplete();
    }

    @Test
    void deleteMovie(){

        List<String> ids = List.of("628656aa4202524fead3d32f");

        Mockito.when(moviedbRepository.deleteAllById(ids))
                .thenReturn(Mono.empty());

        Mono<Void> expect = this.moviedbService.delete(ids);

        StepVerifier
                .create(expect)
                .verifyComplete();
    }
}
