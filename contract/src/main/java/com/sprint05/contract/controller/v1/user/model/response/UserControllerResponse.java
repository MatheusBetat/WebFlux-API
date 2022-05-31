package com.sprint05.contract.controller.v1.user.model.response;

import com.sprint05.impl.repository.entity.MoviedbEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserControllerResponse {

    private String id;

    private String name;

    private List<MoviedbEntity> playlist;

}
