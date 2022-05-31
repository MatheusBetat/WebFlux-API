package com.sprint05.contract.controller.v1.moviedb.mapper.request;

import com.sprint05.contract.controller.v1.moviedb.model.request.MoviedbControllerRequest;
import com.sprint05.impl.moviedb.model.request.MoviedbServiceRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoviedbControllerRequestMapper {

    public static MoviedbServiceRequest mapperMoviedbToService(MoviedbControllerRequest moviedbControllerRequest) {
        return Optional.ofNullable(moviedbControllerRequest)
                .map(moviedbController -> MoviedbServiceRequest.builder()
                        .title(moviedbController.getTitle())
                        .build())
                .orElse(null);
    }
}
