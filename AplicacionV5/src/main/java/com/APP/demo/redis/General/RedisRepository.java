package com.APP.demo.redis.General;

import java.util.Map;

import com.APP.demo.mongo.Documentos.Usuario;

public interface RedisRepository {
	Map<String, SesionUsuario> findAll();
	void save(SesionUsuario sesionUsuario, Usuario usuario);
	SesionUsuario getActividad(String id);
	SesionUsuario actualizarActividad(String id);
}
