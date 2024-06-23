package com.APP.demo.mongo.Documentos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Facturas")
public class Factura {
	@Id
    private String id;
    private String pedidoId;
    private double total;
    private String formaDePago;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(String pedidoId) {
		this.pedidoId = pedidoId;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getFormaDePago() {
		return formaDePago;
	}
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	
	@Override
	public String toString() {
		return "Factura [id=" + id + ", pedidoId=" + pedidoId + ", total=" + total + ", formaDePago=" + formaDePago
				+ "]";
	}
    
}
