package com.APP.demo.mongo.Documentos;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="productos_log")
public class ProductoLog {
	@Id
	private String id;
	private String productoId;
	private String operador;
	private String cambio;
	private double valorAntiguo;
	private double valorNuevo;
	private LocalDateTime fecha;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductoId() {
		return productoId;
	}
	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	public String getCambio() {
		return cambio;
	}
	public void setCambio(String cambio) {
		this.cambio = cambio;
	}
	public double getValorAntiguo() {
		return valorAntiguo;
	}
	public void setValorAntiguo(double valorAntiguo) {
		this.valorAntiguo = valorAntiguo;
	}
	public double getValorNuevo() {
		return valorNuevo;
	}
	public void setValorNuevo(double valorNuevo) {
		this.valorNuevo = valorNuevo;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		return "ProductoLog [id=" + id + ", productoId=" + productoId + ", operador=" + operador + ", cambio=" + cambio
				+ ", valorAntiguo=" + valorAntiguo + ", valorNuevo=" + valorNuevo + ", fecha=" + fecha + "]";
	}
	

}
