package com.APP.demo.mongo.Documentos;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Carritos_versiones")
public class CarritoVersion {
	@Id
    private String id;
	private String id2;
    private String userId;
    private Map<String, Integer> productos;
    private LocalDateTime fecha;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Map<String, Integer> getProductos() {
		return productos;
	}
	public void setProductos(Map<String, Integer> productos) {
		this.productos = productos;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getId2() {
		return id2;
	}
	public void setId2(String id2) {
		this.id2 = id2;
	}
	
	@Override
	public String toString() {
		return "CarritoVersion [Id= " + id + ", Productos=" + productos + ", fecha=" + fecha
				+ "]";
	}
    
	
}
