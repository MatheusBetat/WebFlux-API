package com.sprint05.impl.moviedb.mapper.request;

import com.sprint05.impl.moviedb.model.request.MoviedbServiceRequest;
import com.sprint05.impl.repository.entity.MoviedbEntity;
import com.sprint05.integration.moviedb.model.request.MoviedbIntegrationRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoviedbServiceRequestMapper {

    public static MoviedbIntegrationRequest toMoviedbIntegrationRequest(MoviedbServiceRequest moviedbServiceRequest) {
        return Optional.ofNullable(moviedbServiceRequest)
                .map(moviedbService -> MoviedbIntegrationRequest.builder()
                        .title(moviedbService.getTitle())
                        .build())
                .orElse(null);
    }

    public static MoviedbEntity toMoviedbRequest(MoviedbServiceRequest moviedbServiceRequest) {
        return Optional.ofNullable(moviedbServiceRequest)
                .map(moviedbService -> MoviedbEntity.builder()
                        .title(moviedbService.getTitle())
                        .build())
                .orElse(null);
    }

}
