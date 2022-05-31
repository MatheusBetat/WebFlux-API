package com.sprint05.impl.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "user")
public class UserEntity {

    @Id
    private String id;

    @NotNull
    @NotBlank()
    @Size(min = 20, max = 120, message = "Name with 20-120 characters.")
    private String name;

    private List<MoviedbEntity> playlist = new ArrayList<>();
}
