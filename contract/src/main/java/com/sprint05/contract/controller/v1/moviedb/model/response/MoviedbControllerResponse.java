package com.sprint05.contract.controller.v1.moviedb.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MoviedbControllerResponse {

    private String movieId;

    private String title;

    private String genre;

    private String year;

    private String plot;

    private String type;
}
