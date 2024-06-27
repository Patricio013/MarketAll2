package com.APP.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.util.Scanner;
import com.APP.demo.Menus.*;
import com.APP.demo.mongo.Documentos.Usuario;

@SpringBootApplication(scanBasePackages = "com.APP.demo")
public class AplicacionV5Application implements CommandLineRunner{
	
	@Autowired
    private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(AplicacionV5Application.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
        int numero = 0;
        Usuario usuario = new Usuario();
     // Obtener las instancias gestionadas por Spring
        CreacionUsuario crearUsuario = applicationContext.getBean(CreacionUsuario.class);
        InicioUsuario iniciarSesion = applicationContext.getBean(InicioUsuario.class);
        MenuUsuario menuA = applicationContext.getBean(MenuUsuario.class);
        MenuAdmin menuB = applicationContext.getBean(MenuAdmin.class);
        String texto = """
                Seleccione la opcion que desea ejecutar
                1. Registrarse
                2. Iniciar sesion
                3. Salir
                Escriba el numero de la opcion deseada
                """;

            System.out.println("Bienvenido a MarketAll");
            System.out.println(texto);

            while (numero != 3) {
                try {
                    numero = scanner.nextInt();
                    scanner.nextLine();
                    switch (numero) {
                        case 1:
                        	usuario = crearUsuario.registrarse(scanner);
                        	System.out.println("Usuario Registrado exitosamente");
                        	System.out.println("Redirigiendo al menu");
                        	menuA.MenuAU(scanner, usuario);
                            break;
                        case 2:
                        	usuario = iniciarSesion.Iniciar(scanner);
                        	if (usuario != null) {
                        		if ("USUARIO".equals(usuario.getRol())) {
                        			menuA.MenuAU(scanner, usuario);
                        		} else {
                        			menuB.Administracion(scanner, usuario);
                        		}
                        	}
                            break;
                        case 3:
                            System.out.println("Gracias por usar nuestro sistema");
                            break;
                        default:
                            System.out.println("Opcion no valida");
                    }
                    if (numero!=3) {
                    System.out.println("");
                    System.out.println("Bienvenido devuelta al menu");
                    System.out.println(texto);
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Entrada no válida. Introduce un número entero.");
                    scanner.next(); // Consumir la entrada no válida
                }
            }

            // Cierre del Scanner al final del programa
            scanner.close();
	}

}
