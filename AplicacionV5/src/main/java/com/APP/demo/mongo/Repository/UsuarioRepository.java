package com.APP.demo.mongo.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.APP.demo.mongo.Documentos.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	Usuario findByEmail (String Email);
	Optional <Usuario> findByNombre (String nombre);
}
