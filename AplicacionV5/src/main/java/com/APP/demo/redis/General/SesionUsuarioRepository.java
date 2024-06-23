package com.APP.demo.redis.General;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.APP.demo.mongo.Documentos.Usuario;

import jakarta.annotation.PostConstruct;

@Repository
public class SesionUsuarioRepository implements RedisRepository {

	private static final String KEY = "SesionUsuario";

    private RedisTemplate<String, SesionUsuario> redisTemplate;
    private HashOperations hashOperations;

    public SesionUsuarioRepository(RedisTemplate<String, SesionUsuario> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }
    
	@Override
	public Map<String, SesionUsuario> findAll() {
		return hashOperations.entries(KEY);
	}

	@Override
	public void save(SesionUsuario sesionUsuario, Usuario usuario) {
		hashOperations.put(KEY, usuario.getId(), sesionUsuario);

	}

	@Override
	public SesionUsuario getActividad(String id) {
		return (SesionUsuario) hashOperations.get(KEY, id);
	}

	@Override
	public SesionUsuario actualizarActividad(String id) {
		SesionUsuario sesionUsuario = (SesionUsuario) hashOperations.get(KEY, id);
		sesionUsuario.setUltimaActividad(System.currentTimeMillis());
		return sesionUsuario;
	}

}
