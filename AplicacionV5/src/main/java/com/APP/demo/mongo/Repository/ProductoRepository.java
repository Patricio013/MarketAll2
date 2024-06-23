package com.APP.demo.mongo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.APP.demo.mongo.Documentos.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String>{
	Optional<Producto> findById (String id);
	Producto findByNombre (String nombre);
	Producto deleteByNombre (String nombre);
	List<Producto> findByCategoria(String categoria);
}
