package com.APP.demo.mongo.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.APP.demo.mongo.Documentos.Pedido;
import java.util.List;

public interface PedidosRepository extends MongoRepository<Pedido, String>{
	Optional<Pedido> findById (String id);

	List<Pedido> findAllById(String id);
	
	List<Pedido> findAllByUserId (String userId);
}
