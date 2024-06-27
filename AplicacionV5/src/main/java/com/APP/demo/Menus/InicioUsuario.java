package com.APP.demo.Menus;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.APP.demo.mongo.Repository.UsuarioRepository;
import com.APP.demo.mongo.Documentos.*;

@Component
public class InicioUsuario {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario Iniciar(Scanner scanner) {
		int num2=0;
		String email;
		String contraseña;
		Usuario usuario = new Usuario();
		System.out.println("Ingrese su email");
		email = scanner.next();
		System.out.println("Ingrese su contraseña");
		contraseña = scanner.next();
		usuario = usuarioRepository.findByEmail(email);
		while (usuario == null) {
			System.out.println("El email es incorrecto o no existe");
			System.out.println("Ingrese 1 para volver a intentarlo o 2 para salir");
			num2 = scanner.nextInt();
			if (num2 == 1) {
				System.out.println("Ingrese su email");
				email = scanner.next();
				usuario = usuarioRepository.findByEmail(email);
			}
		}
		while ((!contraseña.equals(usuario.getPassword())) && ((num2==0) || (num2==1))){
			System.out.println("La contraseña es incorrecto");
			System.out.println("Ingrese 1 para volver a intentarlo o 2 para salir");
			num2 = scanner.nextInt();
			if (num2 == 1) {
				System.out.println("Ingrese su contraseña");
				email = scanner.next();
			}
		}
		if (num2==2) {
			usuario=null;
		}
		return usuario;
	}
}
