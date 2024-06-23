package com.APP.demo.mongo.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.APP.demo.mongo.Repository.CarritoRepository;
import com.APP.demo.mongo.Repository.CarritoVersionRepository;
import com.APP.demo.mongo.Repository.PedidosRepository;
import com.APP.demo.mongo.Repository.ProductoRepository;
import com.APP.demo.mongo.Documentos.*;

@Service
public class PedidosService {
	@Autowired
	private CarritoRepository carritoRepository;
	
	@Autowired
	private PedidosRepository pedidoRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired 
	private CarritoVersionRepository carritoAntRepository;
	
	public Pedido crearPedidoDesdeCarrito(Usuario usuario, String condicionIVA, Carrito carrito) {
        Pedido pedido = new Pedido();
        CarritoVersion carritoAntiguo = new CarritoVersion();
        pedido.setId(UUID.randomUUID().toString());
        pedido.setUserId(usuario.getId());
        pedido.setProductos(carrito.getProductos());
        pedido.setTotal(calcularTotal(carrito));
        pedido.setFecha(LocalDateTime.now());
        pedido.setNombre(usuario.getNombre());
        pedido.setApellido(usuario.getApellido());
        pedido.setDireccion(usuario.getDireccion());
        pedido.setCondicionIVA(condicionIVA);
        carritoAntiguo.setFecha(LocalDateTime.now());
        carritoAntiguo.setProductos(carrito.getProductos());
        carritoAntiguo.setId(UUID.randomUUID().toString());
        carritoAntiguo.setUserId(usuario.getId());
        carritoAntiguo.setId2(carritoAntiguo.getId());
        carritoAntRepository.save(carritoAntiguo);
        carritoRepository.delete(carrito);
        return pedidoRepository.save(pedido);
    }
	
	public List<Pedido> obtenerPedidos(Usuario usuario){
		return pedidoRepository.findAllByUserId(usuario.getId());
	}
	
	 private double calcularTotal(Carrito carrito) {
		 double total = 0.0;
	        for (Map.Entry<String, Integer> entry : carrito.getProductos().entrySet()) {
	            String nProduct = entry.getKey();
	            int quantity = entry.getValue();
	            Producto producto = productoRepository.findByNombre(nProduct);
	            double precioUnitario = producto.getPrecio();
	            total += precioUnitario * quantity;
	        }
	    return total;
	 }
	 
	
}
