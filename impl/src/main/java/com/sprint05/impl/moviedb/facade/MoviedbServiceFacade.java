package com.sprint05.impl.moviedb.facade;

import com.sprint05.impl.moviedb.model.request.MoviedbServiceRequest;
import com.sprint05.impl.moviedb.model.response.MoviedbServiceResponse;
import com.sprint05.impl.moviedb.service.MoviedbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@AllArgsConstructor
public class MoviedbServiceFacade {

    private final MoviedbService moviedbService;

    public Mono<MoviedbServiceResponse> getMovie(MoviedbServiceRequest moviedbServiceRequest) {
        return moviedbService.getMovie(moviedbServiceRequest);
    }

    public Mono<MoviedbServiceResponse> getMyMovie(String movieId){
        return moviedbService.getMyMovie(movieId);
    }

    public Flux<MoviedbServiceResponse> getAllMovie() {
        return moviedbService.getAll();
    }

    public Flux<MoviedbServiceResponse> getMoviesByIds(List<String> ids) {
        return moviedbService.findOneOrMany(ids);

    }

    public Mono<Void> deleteMoviesByIds(List<String> ids){
        return moviedbService.delete(ids);
    }
}
