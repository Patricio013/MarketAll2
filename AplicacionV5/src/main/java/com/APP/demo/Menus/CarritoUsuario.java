package com.APP.demo.Menus;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.APP.demo.mongo.Documentos.Usuario;
import com.APP.demo.mongo.Services.CarritoServices;
import com.APP.demo.mongo.Services.FacturaService;
import com.APP.demo.mongo.Services.PedidosService;
import com.APP.demo.redis.General.SesionUsuarioRepository;
import com.APP.demo.mongo.Documentos.*;

@Component
public class CarritoUsuario {
	
	@Autowired
	private CarritoServices carritoServices;
	
	@Autowired
	private PedidosService pedidosService;
	
	@Autowired
	private FacturaService facturaService;
	
	@Autowired
    private SesionUsuarioRepository sesionService;
	
	public void MCarrito (Scanner scanner, Usuario usuario) {
		int numero = 0;
		String temporal;
		Carrito carrito = new Carrito();
		Pedido pedidoA = new Pedido();
		List<Pedido> pedidos;
		List<CarritoVersion> carritosAntiguos;
		String texto = """
                Seleccione la opcion que desea ejecutar
                1. Ver productos en el carrito
                2. Cambiar cantidad de un producto
                3. Sacar producto
                4. Pasar carrito a pedido
                5. Facturar pedidos
                6. Ver carritos antiguos
                7. Volver
                Escriba el numero de la opcion deseada
                """;
		String texto2= """
                Seleccione la opcion que desea ejecutar
                1. Facturar un pedido
                2. Dejar de facturar
                Escriba el numero de la opcion deseada
                """;
		String texto3=
				"""
                Seleccione su forma de pago
                1. Efectivo
                2. Credito (Son solo 3 cuotas)
                3. Debito
                Escriba el numero de la opcion deseada
                """;

            System.out.println("Bienvenido a tu carrito");
            System.out.println(texto);

            while (numero != 7) {
                try {
                    numero = scanner.nextInt();
                    scanner.nextLine();
                    sesionService.actualizarActividad(usuario.getId());
                    switch (numero) {
                        case 1:
                        	carrito = carritoServices.obtenerCarrito(usuario.getId());
                        	System.out.println(carrito);
                            break;
                        case 2:
                        	System.out.println("Ingrese el nombre del producto que quiere modificar");
                        	String nombreProduct = scanner.nextLine();
                        	System.out.println("Ingrese la nueva cantidad");
                        	int cant = scanner.nextInt();
                        	nombreProduct = nombreProduct.replaceAll(" +", " ").trim().toUpperCase();
                        	carritoServices.ActualizarProdCant(usuario.getId(), nombreProduct, cant);
                        	System.out.println("Se actualizo la cantidad");
                            break;
                        case 3:
                        	System.out.println("Ingrese el nombre del producto que quiere sacar");
                        	String nombreProduct1 = scanner.nextLine();
                        	nombreProduct1 = nombreProduct1.replaceAll(" +", " ").trim().toUpperCase();
                        	carrito = carritoServices.quitarProducto(usuario.getId(), nombreProduct1);
                        	System.out.println("Se quito el producto");
                        	System.out.println(carrito);
                            break;
                        case 4:
                        	carrito = carritoServices.obtenerCarrito(usuario.getId());
                        	if (carrito.getProductos() != null) {
                        		pedidoA = pedidosService.crearPedidoDesdeCarrito(usuario, "Responsable Inscripto", carrito);
                        		System.out.println(pedidoA);
                        	} else {
                        		System.out.println("No tiene productos en el carrito");
                        	}
                            break;
                        case 5:
                        	pedidos = pedidosService.obtenerPedidos(usuario);
                        	if (pedidos.isEmpty()) {
                        		System.out.println("No tiene pedidos");
                        	} else {
                        		int num2= 0;
                        		for (Pedido pedido: pedidos) {
                        			System.out.println(pedido);
                        		}
                            	System.out.println(texto2);
                            	while (num2!=2) {
                            		try {
                            			num2= scanner.nextInt();
                            			switch(num2) {
                            			case 1:
                            				System.out.println("Ingrese el ID del pedido que desea facturar");
                            				String pedidoId = scanner.next();
                            				System.out.println(texto3);
                            				int fdpago= scanner.nextInt();
                            				if (fdpago==1) {
                            					temporal = "Efectivo";
                            				} else {
                            					if (fdpago==2) {
                            						temporal = "Credito";
                            					} else {
                            						temporal = "Debito";
                            					}
                            				}
                            				facturaService.facturarPedido(pedidoId, temporal);
                            				System.out.println("Pedido facturado");
                            				break;
                            			case 2:
                            				System.out.println("Saliendo de la facturacion");
                            				break;
                            			default:
                            				 System.out.println("Opcion no valida");
                            			}
                            			if (num2!=2) {
                            				System.out.println(texto2);
                            			}
                            		} catch (java.util.InputMismatchException e) {
                                        System.out.println("Entrada no válida. Introduce un número entero.");
                                        scanner.next(); // Consumir la entrada no válida
                                    }
                            	}
                        	}
                            break;
                        case 6:
                        	carritosAntiguos= carritoServices.CarritosAntiguos(usuario.getId());
                        	for (CarritoVersion carritoAnt: carritosAntiguos) {
                        		System.out.println(carritoAnt);
                        	}
                        	int num3= 0;
                        	System.out.println("Escriba 1 si quiere recuperar algun carrito. Escriba 2 si no quiere hacer nada");
                        	num3= scanner.nextInt();
                        	scanner.nextLine();
                        	if (num3==1) {
                        		System.out.println("Ingrese el ID del carrito que quiere recuperar");
                        		String id2= scanner.nextLine();
                        		carritoServices.CarritoCambio(id2, usuario.getId());
                        		System.out.println("Se cambio el carrito");
                        	}
                            break;
                        case 7:
                            System.out.println("Volviendo atras");
                            break;
                        default:
                            System.out.println("Opcion no valida");
                    }
                    if (numero!=7) {
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
