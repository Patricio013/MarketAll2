package com.APP.demo.mongo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.APP.demo.mongo.Documentos.Factura;

public interface FacturaRepository extends MongoRepository<Factura, String>{

}
