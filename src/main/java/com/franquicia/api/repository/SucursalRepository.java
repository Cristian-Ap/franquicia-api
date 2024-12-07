package com.franquicia.api.repository;

import com.franquicia.api.model.SucursalModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends ReactiveMongoRepository<SucursalModel, String> {
}
