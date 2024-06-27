package com.APP.demo.mongo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.APP.demo.mongo.Documentos.Usuario;
import com.APP.demo.mongo.Repository.UsuarioRepository;
import com.APP.demo.redis.General.*;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    @Autowired
    private SesionUsuarioRepository sesionService;

	public Usuario registrarUsuario(Usuario usuario) {
		SesionUsuario sesionUsuario = new SesionUsuario();
		sesionUsuario.setNombre(usuario.getNombre());
		sesionUsuario.setDireccion(usuario.getDireccion());
		sesionUsuario.setDocumentoIdentidad(usuario.getDocumentoIdentidad());
		sesionUsuario.setInicioSesion(System.currentTimeMillis());
		sesionUsuario.setUltimaActividad(System.currentTimeMillis());
		sesionService.save(sesionUsuario, usuario);
        return usuarioRepository.save(usuario);
    }
	
	public String UsuariosCategorias (String id) {
		SesionUsuario sesion = sesionService.getActividad(id);
		long sesionDuration = (sesion.getUltimaActividad() - sesion.getInicioSesion()) / (1000*60);
		if (sesionDuration > 240) {
            return "TOP";
        } else if (sesionDuration >= 120) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
	}
	
	public List<Usuario> obtenerTodosUsuario (){
		return usuarioRepository.findAll();
	}

}
