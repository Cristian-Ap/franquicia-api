package com.franquicia.api.repository;

import com.franquicia.api.model.FranquiciaModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranquiciaRepository extends ReactiveMongoRepository<FranquiciaModel, String> {
}
