package com.APP.demo.mongo.Documentos;

import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "Carritos")
public class Carrito {
	@Id
    private String id;
    private String userId;
    private Map<String, Integer> productos;
    
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
	
	@Override
	public String toString() {
		return "Carrito [id=" + id + ", userId=" + userId + ", productos=" + productos + "]";
	}

}
