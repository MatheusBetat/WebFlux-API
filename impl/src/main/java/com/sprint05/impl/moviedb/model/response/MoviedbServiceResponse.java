package com.sprint05.impl.moviedb.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MoviedbServiceResponse {

    private String movieId;

    private String title;

    private String genre;

    private String year;

    private String plot;

    private String type;
}
