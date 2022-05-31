package com.sprint05.impl.moviedb.mapper.response;

import com.sprint05.impl.moviedb.model.response.MoviedbServiceResponse;
import com.sprint05.impl.repository.entity.MoviedbEntity;
import com.sprint05.integration.moviedb.model.response.MoviedbIntegrationResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoviedbServiceResponseMapper {

    public static MoviedbEntity mapToMoviedbEntity(MoviedbIntegrationResponse moviedbIntegrationResponse) {
        return MoviedbEntity.builder()
                .movieId(moviedbIntegrationResponse.getMovieId())
                .title(moviedbIntegrationResponse.getTitle())
                .genre(moviedbIntegrationResponse.getGenre())
                .description(moviedbIntegrationResponse.getPlot())
                .year(moviedbIntegrationResponse.getYear())
                .type(moviedbIntegrationResponse.getType())
                .build();
    }

    public static MoviedbServiceResponse toServiceResponse(MoviedbEntity moviedbEntity) {
        return MoviedbServiceResponse.builder()
                .movieId(moviedbEntity.getMovieId())
                .title(moviedbEntity.getTitle())
                .genre(moviedbEntity.getGenre())
                .plot(moviedbEntity.getDescription())
                .year(moviedbEntity.getYear())
                .type(moviedbEntity.getType())
                .build();
    }

    public static MoviedbServiceResponse integrationToServiceResponse(MoviedbIntegrationResponse moviedbIntegrationResponse){
        return MoviedbServiceResponse.builder()
                .title(moviedbIntegrationResponse.getTitle())
                .genre(moviedbIntegrationResponse.getGenre())
                .plot(moviedbIntegrationResponse.getPlot())
                .year(moviedbIntegrationResponse.getYear())
                .type(moviedbIntegrationResponse.getType())
                .build();
    }

    public static MoviedbEntity mapToMoviedbEntity(MoviedbServiceResponse moviedbServiceResponse) {
        return MoviedbEntity.builder()
                .movieId(moviedbServiceResponse.getMovieId())
                .title(moviedbServiceResponse.getTitle())
                .genre(moviedbServiceResponse.getGenre())
                .year(moviedbServiceResponse.getYear())
                .description(moviedbServiceResponse.getPlot())
                .type(moviedbServiceResponse.getType())
                .build();
    }
}
