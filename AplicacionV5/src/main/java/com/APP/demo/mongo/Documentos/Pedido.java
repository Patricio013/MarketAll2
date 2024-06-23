package com.APP.demo.mongo.Documentos;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Pedidos")
public class Pedido {
	@Id
    private String id;
    private String userId;
    private Map<String, Integer> productos;
    private double total;
    private LocalDateTime fecha;

    // Datos del cliente
    private String nombre;
    private String apellido;
    private String direccion;
    private String condicionIVA;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Map<String, Integer> getProductos() {
		return productos;
	}
	public void setProductos(Map<String, Integer> productos) {
		this.productos = productos;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCondicionIVA() {
		return condicionIVA;
	}
	public void setCondicionIVA(String condicionIVA) {
		this.condicionIVA = condicionIVA;
	}
	
	@Override
	public String toString() {
		return "Pedido [Id=" + id +", Productos=" + productos + ", total=" + total + ", fecha="
				+ fecha + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion
				+ ", condicionIVA=" + condicionIVA + "]";
	}
    
}
