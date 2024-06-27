package com.APP.demo.Menus;

import java.util.Scanner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.APP.demo.mongo.Documentos.Usuario;
import com.APP.demo.mongo.Services.UsuarioService;

@Component
public class CreacionUsuario {
	
	@Autowired 
	private UsuarioService usuarioService;
	
	public Usuario registrarse (Scanner scanner) {
		Usuario usuario = new Usuario();
		System.out.println("Ingrese su nombre (Solo su primer nombre)");
		String nombre = scanner.nextLine();
		System.out.println("Ingrese su apellido");
		String appelido = scanner.nextLine();
		System.out.println("Ingrese su email");
		String email = scanner.nextLine();
		System.out.println("Ingrese su direccion");
		String direccion = scanner.nextLine();
		System.out.println("Ingrese su DNI");
		String documentoIdentidad = scanner.nextLine();
		System.out.println("Ingrese su contraseña");
		String password = scanner.nextLine();
		usuario.setId(UUID.randomUUID().toString());
		usuario.setNombre(nombre);
		usuario.setApellido(appelido);
		usuario.setEmail(email);
		usuario.setDireccion(direccion);
		usuario.setDocumentoIdentidad(documentoIdentidad);
		usuario.setPassword(password);
		usuario.setRol("USUARIO");
		usuario = usuarioService.registrarUsuario(usuario);
		return usuario;
	}
}
