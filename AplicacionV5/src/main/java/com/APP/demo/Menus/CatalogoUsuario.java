package com.APP.demo.Menus;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.APP.demo.mongo.Documentos.Usuario;
import com.APP.demo.mongo.Services.CarritoServices;
import com.APP.demo.mongo.Services.ProductoServices;
import com.APP.demo.redis.General.SesionUsuarioRepository;
import com.APP.demo.mongo.Documentos.*;
import java.util.*;

@Component
public class CatalogoUsuario {
	@Autowired
	private ProductoServices productoService;
	
	@Autowired
	private CarritoServices carritoServices;
	
	@Autowired
    private SesionUsuarioRepository sesionService;
	
	public void Catalogo (Scanner scanner, Usuario usuario) {
		int numero = 0;
		List<Producto> catalogo;
		String texto = """
                Seleccione la opcion que desea ejecutar
                1. Ver productos
                2. Filtrar busqueda
                3. Agregar producto al carrito
                4. Volver
                Escriba el numero de la opcion deseada
                """;

            System.out.println("Bienvenido al Catalogo");
            System.out.println(texto);

            while (numero != 4) {
                try {
                    numero = scanner.nextInt();
                    scanner.nextLine();
                    sesionService.actualizarActividad(usuario.getId());
                    switch (numero) {
                        case 1:
                        	catalogo = productoService.obtenerTodosLosProductos();
                        	for (Producto producto: catalogo) {
                        		System.out.println(producto);
                        	}
                            break;
                        case 2:
                        	//Hacer menu aparte
                        	System.out.println("En desarrollo");
                            break;
                        case 3:
                        	int cant = 1;
                        	String nombreProduct;
                        	System.out.println("Ingrese el nombre del producto");
                        	nombreProduct = scanner.nextLine();
                        	System.out.println("Ingrese la cantidad");
                        	cant = scanner.nextInt();
                        	nombreProduct = nombreProduct.replaceAll(" +", " ").trim().toUpperCase();
                        	carritoServices.agregarProducto(usuario.getId(), nombreProduct, cant);
                        	break;
                        case 4:
                            System.out.println("Volviendo atras");
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
