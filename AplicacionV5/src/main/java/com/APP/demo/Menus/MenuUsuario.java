package com.APP.demo.Menus;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.APP.demo.mongo.Documentos.Usuario;
import com.APP.demo.redis.General.SesionUsuarioRepository;

@Component
public class MenuUsuario {
	
	@Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
    private SesionUsuarioRepository sesionService;

	public void MenuAU (Scanner scanner, Usuario usuario) {
		int numero = 0;
		CatalogoUsuario catalogo = applicationContext.getBean(CatalogoUsuario.class);
		CarritoUsuario carUsuario = applicationContext.getBean(CarritoUsuario.class);
		String texto = """
                Seleccione la opcion que desea ejecutar
                1. Ir al Catalogo
                2. Ir al Carrito
                3. Cerrar sesion
                Escriba el numero de la opcion deseada
                """;

            System.out.println("Bienvenido " + usuario.getNombre() + " " + usuario.getApellido());
            System.out.println(texto);

            while (numero != 3) {
                try {
                	if (!scanner.hasNextInt()) {
                		scanner.next();
                		continue;
                	}
                    numero = scanner.nextInt();
                    sesionService.actualizarActividad(usuario.getId());
                    switch (numero) {
                        case 1:
                        	catalogo.Catalogo(scanner, usuario);
                            break;
                        case 2:
                        	carUsuario.MCarrito(scanner, usuario);
                            break;
                        case 3:
                            System.out.println("Cerrando Sesion");
                            break;
                        default:
                            System.out.println("Opcion no valida");
                    }
                    if (numero!=3) {
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
