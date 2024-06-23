package com.APP.demo.Menus;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.APP.demo.mongo.Documentos.*;
import com.APP.demo.mongo.Repository.PagoLogRepository;
import com.APP.demo.mongo.Services.UsuarioService;
import com.APP.demo.redis.General.SesionUsuarioRepository;

@Component 
public class MenuAdmin {
	
	@Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
	private PagoLogRepository pagologRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private SesionUsuarioRepository sesionService;
	
	public void Administracion (Scanner scanner, Usuario usuario) {
		int numero = 0;
		List<PagoLog> logs;
		List<Usuario> usuarios;
		CatalogoAdmin CatAdmin = applicationContext.getBean(CatalogoAdmin.class);
		String texto = """
                Seleccione la opcion que desea ejecutar
                1. Ir al Catalogo
                2. Ver las operaciones realizadas
                3. Ver usuarios y su categoria
                4. Cerrar sesion
                Escriba el numero de la opcion deseada
                """;
		String texto2= """
                Seleccione la opcion que desea ejecutar
                1. Ver categoria de algun usuario
                2. Volver
                Escriba el numero de la opcion deseada
                """;
		System.out.println("Bienvenido " + usuario.getNombre() + " " + usuario.getApellido());
        System.out.println(texto);
        while (numero != 4) {
        	try {
                numero = scanner.nextInt();
                sesionService.actualizarActividad(usuario.getId());
                switch (numero) {
                    case 1:
                    	CatAdmin.CAdmin(scanner, usuario);
                        break;
                    case 2:
                    	logs = pagologRepository.findAll();
                    	for (PagoLog logPago: logs) {
                    		System.out.println(logPago);
                    	}
                        break;
                    case 3:
                    	usuarios = usuarioService.obtenerTodosUsuario();
                    	for (Usuario usu: usuarios) {
                    		System.out.println(usu);
                    	}
                    	int num2 = 0;
                    	System.out.println(texto2);
                    	while (num2!=2) {
                    		try {
                    			num2= scanner.nextInt();
                    			switch(num2) {
                    			case 1:
                    				System.out.println("Ingrese ID de algun usuario");
                    				String id = scanner.next();
                    				String categoria = usuarioService.UsuariosCategorias(id);
                    				System.out.println(categoria);
                    				break;
                    			case 2:
                    				System.out.println("Volviendo atras");
                    				break;
                    			default:
                   				 System.out.println("Opcion no valida");
                    			}
                    			if (num2 != 2) {
                    				System.out.println(texto2);
                    			}
                    		} catch (java.util.InputMismatchException e) {
                                System.out.println("Entrada no válida. Introduce un número entero.");
                                scanner.next(); // Consumir la entrada no válida
                            }
                    	}
                        break;
                    case 4:
                        System.out.println("Cerrando Sesion");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
                if (numero!=4) {
                System.out.println("");
                System.out.println("Bienvenido devuelta al menu");
                System.out.println(texto);
                sesionService.actualizarActividad(usuario.getId());
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada no válida. Introduce un número entero.");
                scanner.next(); // Consumir la entrada no válida
            }
        }
	}
}
