package com.sprint05.contract.controller.v1.moviedb.mapper.response;

import com.sprint05.contract.controller.v1.moviedb.model.response.MoviedbControllerResponse;
import com.sprint05.impl.moviedb.model.response.MoviedbServiceResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoviedbControllerResponseMapper {

    public static MoviedbControllerResponse mapToResponse(MoviedbServiceResponse moviedbServiceResponse) {
        return MoviedbControllerResponse.builder()
                .movieId(moviedbServiceResponse.getMovieId())
                .title(moviedbServiceResponse.getTitle())
                .genre(moviedbServiceResponse.getGenre())
                .plot(moviedbServiceResponse.getPlot())
                .type(moviedbServiceResponse.getType())
                .year(moviedbServiceResponse.getYear())
                .build();
    }
}
