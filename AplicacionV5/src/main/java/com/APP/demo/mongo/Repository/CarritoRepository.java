package com.APP.demo.mongo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.APP.demo.mongo.Documentos.Carrito;
import java.util.Optional;

public interface CarritoRepository extends MongoRepository<Carrito, String>{
	Optional<Carrito> findByUserId(String userId);
}
