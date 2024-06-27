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
	String filtro1Str;
    double filtro1;
    String filtro2Str;
    double filtro2;
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
	String texto4 = """
            Seleccione que quiere modificar
            1. Nombre
            2. Descripcion 
            3. Precio
            4. Comentarios
            5. Volver atras
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
					categoria = "SIN CATEGORIA";
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
					productos = productoServices.obtenerTodosLosProductos();
					int num2= 0;
					while (num2!=4) {
						try {
							num2= scanner.nextInt();
							scanner.nextLine();
							switch (num2) {
							case 1:
								System.out.println("Ingrese el precio");
								filtro1Str= scanner.nextLine();
								filtro1 = Double.parseDouble(filtro1Str.replace(",", "."));
								for (Producto producto: productos) {
									if (filtro1<producto.getPrecio()) {
										System.out.println(producto);
									}
			                	}
								break;
							case 2:
								System.out.println("Ingrese el precio");
								filtro1Str= scanner.nextLine();
								filtro1 = Double.parseDouble(filtro1Str.replace(",", "."));
								for (Producto producto: productos) {
									if (filtro1>producto.getPrecio()) {
										System.out.println(producto);
									}
			                	}
								break;
							case 3:
								System.out.println("Ingrese el primer precio");
								filtro1Str= scanner.nextLine();
								filtro1 = Double.parseDouble(filtro1Str.replace(",", "."));
								System.out.println("Ingrese el segundo precio");
								filtro2Str= scanner.nextLine();
								filtro2 = Double.parseDouble(filtro2Str.replace(",", "."));
								for (Producto producto: productos) {
									if (filtro1>filtro2) {
										if ((filtro2<producto.getPrecio()) && (producto.getPrecio()<filtro1)) {
											System.out.println(producto);
										}
									} else {
										if ((filtro2>producto.getPrecio()) && (producto.getPrecio()>filtro1)) {
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
	
	public void Modificiar (Scanner scanner, Usuario usuario) {
		numero = 0;
		String cambios = "Se cambio ";
		System.out.println("1 para Modificar. 2 Para volver atras.");
		while (numero != 2) {
			try {
				numero = scanner.nextInt();
                scanner.nextLine();
				switch (numero) {
				case 1:
					System.out.println("Ingrese el nombre del producto a modificar");
					String nombreProducto = scanner.nextLine();
					nombreProducto = nombreProducto.replaceAll(" +", " ").trim().toUpperCase();
					Producto producto = productoServices.obtenerProducto(nombreProducto);
					String nomNuevo = producto.getNombre();
					String desNuevo = producto.getDescripcion();
					double precioNuevo = producto.getPrecio();
					String comNuevo = producto.getComentarios();
					int num3=0;
					System.out.println(texto4);
					while (num3!=5) {
						try {
							num3 = scanner.nextInt();
							scanner.nextLine();
							switch (num3) {
							case 1:
								System.out.println("Ingrese el nuevo nombre");
								nomNuevo = scanner.nextLine();
								nomNuevo = nomNuevo.replaceAll(" +", " ").trim().toUpperCase();
								cambios = cambios + "Nombre ";
								break;
							case 2:
								System.out.println("Ingrese la nueva descripcion");
								desNuevo = scanner.nextLine();
								cambios = cambios + "Descripcion ";
								break;
							case 3:
								System.out.println("Ingrese el nuevo precio");
								String precioNuevoStr = scanner.nextLine();
								precioNuevo = Double.parseDouble(precioNuevoStr.replace(",", "."));
								cambios = cambios + "Precio ";
								break;
							case 4:
								System.out.println("Ingrese el nuevo comentario");
								comNuevo = scanner.nextLine();
								cambios = cambios + "Comentario ";
								break;
							case 5:
								System.out.println("Volviendo atras");
								break;
							default:
								System.out.println("Opcion no valida");
							}
							if (numero!=5) {
				                System.out.println("");
				                System.out.println(texto4);
							}
						} catch (java.util.InputMismatchException e) {
			                System.out.println("Entrada no válida. Introduce un número entero.");
			                scanner.next(); // Consumir la entrada no válida
			            }
					}
					if (! cambios.equals("Se cambio ")) {
						productoServices.modificarProducto(nombreProducto, nomNuevo, desNuevo, precioNuevo, comNuevo, usuario.getNombre(), cambios);
					}
					break;
				case 2:
					System.out.println("Volviendo atras");
					break;
				default:
					System.out.println("Opcion no valida");
				}
				if (numero!=2) {
	                System.out.println("");
	                System.out.println("1 para Modificar. 2 Para volver atras.");
				}
			} catch (java.util.InputMismatchException e) {
                System.out.println("Entrada no válida. Introduce un número entero.");
                scanner.next(); // Consumir la entrada no válida
            }
		}
	}
}
