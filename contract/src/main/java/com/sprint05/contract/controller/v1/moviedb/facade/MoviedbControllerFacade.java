package com.sprint05.contract.controller.v1.moviedb.facade;

import com.sprint05.contract.controller.v1.moviedb.mapper.response.MoviedbControllerResponseMapper;
import com.sprint05.contract.controller.v1.moviedb.model.request.MoviedbControllerRequest;
import com.sprint05.contract.controller.v1.moviedb.model.response.MoviedbControllerResponse;
import com.sprint05.impl.moviedb.facade.MoviedbServiceFacade;
import com.sprint05.impl.repository.entity.MoviedbEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.sprint05.contract.controller.v1.moviedb.mapper.request.MoviedbControllerRequestMapper.mapperMoviedbToService;

@Component
@AllArgsConstructor
public class MoviedbControllerFacade {

    private final MoviedbServiceFacade moviedbServiceFacade;

    public Mono<MoviedbControllerResponse> getMovie(MoviedbControllerRequest moviedbControllerRequest) {
        return moviedbServiceFacade.getMovie(mapperMoviedbToService(moviedbControllerRequest))
                .map(MoviedbControllerResponseMapper::mapToResponse);
    }

    public Mono<MoviedbControllerResponse> getMyMovie(String movieId){
        return moviedbServiceFacade.getMyMovie(movieId)
                .map(MoviedbControllerResponseMapper::mapToResponse);

    }
    public Flux<MoviedbControllerResponse> getAllMovie() {
        return moviedbServiceFacade.getAllMovie()
                .map(MoviedbControllerResponseMapper::mapToResponse);

    }

    public Flux<MoviedbControllerResponse> getMovieByIds(List<String> ids) {
        return moviedbServiceFacade.getMoviesByIds(ids)
                .map(MoviedbControllerResponseMapper::mapToResponse);
    }

    public Mono<Void> deleteMoviesByIds(List<String> ids){
        return moviedbServiceFacade.deleteMoviesByIds(ids);
    }
}
