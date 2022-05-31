package com.sprint05.impl.repository;

import com.sprint05.impl.repository.entity.MoviedbEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviedbRepository extends ReactiveMongoRepository<MoviedbEntity, String> {


}
