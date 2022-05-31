package com.sprint05.contract.controller.v1.moviedb.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MoviedbControllerRequest {

    private String title;

}
