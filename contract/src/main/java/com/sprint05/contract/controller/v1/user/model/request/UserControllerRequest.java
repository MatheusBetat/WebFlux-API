package com.sprint05.contract.controller.v1.user.model.request;

import com.sprint05.impl.repository.entity.MoviedbEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserControllerRequest {

    @NotNull
    @NotBlank()
    @Size(min = 20, max = 120, message = "Name with 20-120 characters.")
    private String name;

    private List<MoviedbEntity> playlist = new ArrayList<>();

}
