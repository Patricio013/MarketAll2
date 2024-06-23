package com.APP.demo.mongo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.APP.demo.mongo.Documentos.PagoLog;

public interface PagoLogRepository extends MongoRepository<PagoLog, String>{

}
