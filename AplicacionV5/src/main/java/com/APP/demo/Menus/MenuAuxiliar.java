package com.APP.demo.Menus;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.APP.demo.mongo.Documentos.*;
import com.APP.demo.mongo.Services.ProductoServices;

@Component
public class MenuAuxiliar {
	
	@Autowired
	private ProductoServices productoServices;
	
	String texto = """
            Filtrar por
            1. Categoria
            2. Precio
            3. Volver atras
            Escriba el numero de la opcion deseada
            """;
	String categoria;
	double filtronum=0;
	int numero = 0;
	String texto2 = """
            Seleccione la categoria del producto
            1. Alimento
            2. Bebida
            3. Limpieza
            4. Electrodomestico
            5. Golosinas
            Escriba el numero de la opcion deseada
            """;
	String texto3 = """
            Seleccione como quiere el precio
            1. Mayor a 
            2. Menor a 
            3. Entre
            4. Volver atras
            Escriba el numero de la opcion deseada
            """;
	List<Producto> productos;
	
	public String Categorias (Scanner scanner) {
		System.out.println(texto2);
		numero = scanner.nextInt();
		if (numero == 1) {
			categoria = "ALIMENTO";
		} else {
			if (numero==2) {
				categoria= "BEBIDA";
			} else {
				if (numero ==3) {
					categoria= "LIMPIEZA";
				} else {
					if (numero == 4 ) {
						categoria = "ELECTRODOMESTICO";
					} else {
						if (numero == 5 ) {
							categoria = "GOLOSINAS";
						} else {
							categoria = "SIN CATEGORIA";
						}
					}
				}
			}
		}
		scanner.nextLine();
		return categoria;
	}
	
	public void Filtro (Scanner scanner) {
		System.out.println(texto);
		numero = 0;
		categoria = "SIN CATEGORIA";
		while (numero !=3 ) {
			try {
				numero = scanner.nextInt();
                scanner.nextLine();
				switch (numero) {
				case 1:
					while (categoria.equals("SIN CATEGORIA")) {
                		categoria = Categorias(scanner);
                	}
					productos = productoServices.obtenerProductoPorCategoria(categoria);
					for (Producto producto: productos) {
                		System.out.println(producto);
                	}
					break;
				case 2:
					System.out.println(texto3);
					int num2= 0;
					while (num2!=4) {
						try {
							num2= scanner.nextInt();
							scanner.nextLine();
							switch (num2) {
							case 1:
								System.out.println("Ingrese el precio");
								filtronum= scanner.nextDouble();
								for (Producto producto: productos) {
									if (filtronum<producto.getPrecio()) {
										System.out.println(producto);
									}
			                	}
								break;
							case 2:
								System.out.println("Ingrese el precio");
								filtronum= scanner.nextDouble();
								for (Producto producto: productos) {
									if (filtronum>producto.getPrecio()) {
										System.out.println(producto);
									}
			                	}
								break;
							case 3:
								System.out.println("Ingrese el primer precio");
								filtronum= scanner.nextDouble();
								System.out.println("Ingrese el segundo precio");
								double filtronum2= scanner.nextDouble();
								for (Producto producto: productos) {
									if (filtronum>filtronum2) {
										if ((filtronum2<producto.getPrecio()) && (producto.getPrecio()<filtronum)) {
											System.out.println(producto);
										}
									} else {
										if ((filtronum2>producto.getPrecio()) && (producto.getPrecio()>filtronum)) {
											System.out.println(producto);
										}
									}
			                	}
								break;
							case 4:
								System.out.println("Volviendo atras");
								break;
							default:
								System.out.println("Opcion no valida");
							}
							if (numero!=4) {
				                System.out.println("");
				                System.out.println(texto3);
							}
						} catch (java.util.InputMismatchException e) {
			                System.out.println("Entrada no válida. Introduce un número entero.");
			                scanner.next(); // Consumir la entrada no válida
			            }
					}
					break;
				case 3:
					System.out.println("Volviendo atras");
					break;
				default:
					System.out.println("Opcion no valida");
				}
				if (numero!=3) {
	                System.out.println("");
	                System.out.println(texto);
				}
			}catch (java.util.InputMismatchException e) {
                System.out.println("Entrada no válida. Introduce un número entero.");
                scanner.next(); // Consumir la entrada no válida
            }
		}
	}
}
