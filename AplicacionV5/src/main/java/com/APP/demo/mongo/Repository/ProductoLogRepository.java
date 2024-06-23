package com.APP.demo.mongo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.APP.demo.mongo.Documentos.ProductoLog;

public interface ProductoLogRepository extends MongoRepository<ProductoLog, String>{

}
