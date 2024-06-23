package com.APP.demo.Menus;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.APP.demo.mongo.Documentos.*;
import com.APP.demo.mongo.Repository.ProductoLogRepository;
import com.APP.demo.mongo.Services.ProductoServices;
import com.APP.demo.redis.General.SesionUsuarioRepository;

@Component
public class CatalogoAdmin {

	@Autowired
	private ProductoServices productoServices;
	
	@Autowired 
	private ProductoLogRepository producLogRepository;
	
	@Autowired
    private SesionUsuarioRepository sesionService;
	
	@Autowired
    private ApplicationContext applicationContext;
	
	public void CAdmin (Scanner scanner, Usuario usuario) {
		int numero = 0;
		List<Producto> catalogo;
		List<ProductoLog> logs;
		MenuAuxiliar menuAuxiliar = applicationContext.getBean(MenuAuxiliar.class);
		String categoria = "SIN CATEGORIA";
		String texto = """
                Seleccione la opcion que desea ejecutar
                1. Ver catalogo
                2. Agregar producto
                3. Modificiar producto
                4. Borrar producto
                5. Ver logs de productos
                6. Volver atras
                Escriba el numero de la opcion deseada
                """;
		System.out.println("Bienvenido al menu del Catalogo");
        System.out.println(texto);
        while (numero != 6) {
        	try {
                numero = scanner.nextInt();
                scanner.nextLine();
                sesionService.actualizarActividad(usuario.getId());
                switch (numero) {
                    case 1:
                    	catalogo = productoServices.obtenerTodosLosProductos();
                    	for (Producto producto: catalogo) {
                    		System.out.println(producto);
                    	}
                        break;
                    case 2:
                    	while (categoria.equals("SIN CATEGORIA")) {
                    		categoria = menuAuxiliar.Categorias(scanner);
                    	}
                    	System.out.println("Ingrese nombre del producto");
                    	String nombre = scanner.nextLine();
                    	System.out.println("Ingrese la descripcion del producto");
                    	String descripcion = scanner.nextLine();
                    	System.out.println("Ingrese el precio del producto");
                    	double precio = scanner.nextDouble();
                    	scanner.nextLine();
                    	System.out.println("Ingrese algun comentario");
                    	String comentarios = scanner.nextLine();
                    	nombre = nombre.replaceAll(" +", " ").trim().toUpperCase();
                    	productoServices.crearProducto(nombre, descripcion, precio, comentarios);
                    	System.out.println("Producto creado");
                        break;
                    case 3:
                    	menuAuxiliar.Filtro(scanner);
                        break;
                    case 4:
                    	System.out.println("Ingrese nombre del producto");
                    	String nombre2 = scanner.next();
                    	nombre2 = nombre2.replaceAll(" +", " ").trim().toUpperCase();
                    	productoServices.borrarProducto(nombre2);
                    	System.out.println("Producto borrado");
                    	break;
                    case 5:
                    	logs = producLogRepository.findAll();
                    	for (ProductoLog logpro: logs) {
                    		System.out.println(logpro);
                    	}
                    	break;
                    case 6:
                        System.out.println("Cerrando Sesion");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
                if (numero!=6) {
                System.out.println("");
                System.out.println("Bienvenido devuelta al menu del Catalogo");
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
