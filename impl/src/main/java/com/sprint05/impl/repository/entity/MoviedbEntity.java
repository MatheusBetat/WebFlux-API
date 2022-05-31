package com.sprint05.impl.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "moviedb")
public class MoviedbEntity {

    @Id
    private String movieId;

    private String title;

    private String genre;

    private String year;

    private String description;

    private String type;


}
