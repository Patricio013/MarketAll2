package com.APP.demo.redis.General;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SesionController {
	private SesionUsuarioRepository sesionRepository;

	public SesionController(SesionUsuarioRepository sesionRepository) {
		super();
		this.sesionRepository = sesionRepository;
	}
	
	@GetMapping("/TodasActividades")
	public Map<String, SesionUsuario> findAll(){
		return sesionRepository.findAll();
	}
	
	@GetMapping("/Actividad/{id}")
	public SesionUsuario ActividadUsuario(@PathVariable String id) {
		return sesionRepository.getActividad(id);
	}
	
	@PostMapping("/Actualizar/{id}")
	public SesionUsuario actualizar(@PathVariable String id) {
		return sesionRepository.actualizarActividad(id);
	}
	
	@GetMapping("/categoria/{id}")
	public String getCategoria(@PathVariable String id) {
		SesionUsuario sesion = sesionRepository.getActividad(id);
		long sesionDuration = (sesion.getUltimaActividad() - sesion.getInicioSesion()) / (1000*60);
		if (sesionDuration > 240) {
            return "TOP";
        } else if (sesionDuration >= 120) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
	}
}
