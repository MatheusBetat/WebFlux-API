package com.sprint05.impl.moviedb.service;

import com.sprint05.impl.moviedb.mapper.response.MoviedbServiceResponseMapper;
import com.sprint05.impl.moviedb.model.request.MoviedbServiceRequest;
import com.sprint05.impl.moviedb.model.response.MoviedbServiceResponse;
import com.sprint05.impl.repository.MoviedbRepository;
import com.sprint05.integration.moviedb.model.MoviedbIntegration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.sprint05.impl.moviedb.mapper.request.MoviedbServiceRequestMapper.*;

@AllArgsConstructor
@Service
public class MoviedbService {

    private final MoviedbIntegration moviedbIntegration;

    private final MoviedbRepository moviedbRepository;

    public Mono<MoviedbServiceResponse> getMovie(MoviedbServiceRequest moviedbServiceRequest) {

        return moviedbIntegration.getMovie(toMoviedbIntegrationRequest(moviedbServiceRequest))
                .map(MoviedbServiceResponseMapper::mapToMoviedbEntity)
                .flatMap(moviedbRepository::save)
                .map(MoviedbServiceResponseMapper::toServiceResponse);
    }

    public Mono<MoviedbServiceResponse> getMyMovie(String movieId){
        return moviedbRepository.findById(movieId)
                .map(MoviedbServiceResponseMapper::toServiceResponse);
    }

    public Flux<MoviedbServiceResponse> getAll() {
        return moviedbRepository.findAll()
                .map(MoviedbServiceResponseMapper::toServiceResponse)
                .switchIfEmpty(Flux.empty());

    }

    public Flux<MoviedbServiceResponse> findOneOrMany(List<String> ids) {
        return moviedbRepository.findAllById(ids)
                .map(MoviedbServiceResponseMapper::toServiceResponse);

    }

    public Mono<Void> delete(List<String> ids) {
        return moviedbRepository.deleteAllById(ids);

    }
}
