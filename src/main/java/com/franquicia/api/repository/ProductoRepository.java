package com.franquicia.api.repository;

import com.franquicia.api.model.ProductoModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends ReactiveMongoRepository<ProductoModel, String> {
}
