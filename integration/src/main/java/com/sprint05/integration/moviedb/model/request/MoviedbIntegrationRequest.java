package com.sprint05.integration.moviedb.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MoviedbIntegrationRequest {

    @JsonProperty("t")
    private String title;
}
