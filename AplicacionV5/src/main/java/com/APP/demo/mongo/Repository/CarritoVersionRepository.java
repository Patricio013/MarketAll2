package com.APP.demo.mongo.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.APP.demo.mongo.Documentos.CarritoVersion;

public interface CarritoVersionRepository extends MongoRepository<CarritoVersion, String> {
    List<CarritoVersion> findByUserIdOrderByFechaDesc(String userId);
    CarritoVersion findById2 (String id2);
}
