package com.APP.demo.mongo.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.APP.demo.mongo.Documentos.Producto;
import com.APP.demo.mongo.Documentos.ProductoLog;
import com.APP.demo.mongo.Repository.ProductoRepository;
import com.APP.demo.mongo.Repository.ProductoLogRepository;

@Service
public class ProductoServices {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private ProductoLogRepository productologRepository;
	
	public Producto crearProducto(String nombre, String descripcion, double precio, String comentarios) {
		Producto producto = new Producto();
		producto.setId(UUID.randomUUID().toString());
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setPrecio(precio);
		producto.setComentarios(comentarios);
		return productoRepository.save(producto);
	}
	
	public List<Producto> obtenerTodosLosProductos(){
		return productoRepository.findAll();
	}
	
	public Producto obtenerProducto(String nProduct) {
		return productoRepository.findByNombre(nProduct);
	}
	
	public void borrarProducto (String nombre) {
		productoRepository.deleteByNombre(nombre);
	}
	
	public List<Producto> obtenerProductoPorCategoria (String categoria){
		return productoRepository.findByCategoria(categoria);
	}
	
	public Producto modificarProducto(String nProduct, String nombre, String descripcion, double valorNuevo, String comentarios, String User, String Cambios) {
		Producto producto = obtenerProducto(nProduct);
		ProductoLog productolog = new ProductoLog();
		productolog.setId(UUID.randomUUID().toString());
		productolog.setProductoId(producto.getId());
		productolog.setValorAntiguo(producto.getPrecio());
		productolog.setValorNuevo(valorNuevo);
		productolog.setOperador(User);
		productolog.setFecha(LocalDateTime.now());
		productolog.setCambio(Cambios);
		producto.setPrecio(valorNuevo);
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setComentarios(comentarios);
		productologRepository.save(productolog);
		return productoRepository.save(producto);
	}
	
}
