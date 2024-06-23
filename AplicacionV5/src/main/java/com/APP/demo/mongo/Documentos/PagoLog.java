package com.APP.demo.mongo.Documentos;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="pagos_log")
public class PagoLog {
	@Id
	private String id;
	private String facturaId;
    private String medio;
    private String operador;
    private LocalDateTime fecha;
    private double monto;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFacturaId() {
		return facturaId;
	}
	public void setFacturaId(String facturaId) {
		this.facturaId = facturaId;
	}
	public String getMedio() {
		return medio;
	}
	public void setMedio(String medio) {
		this.medio = medio;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	@Override
	public String toString() {
		return "PagoLog [id=" + id + ", facturaId=" + facturaId + ", medio=" + medio + ", operador=" + operador
				+ ", fecha=" + fecha + ", monto=" + monto + "]";
	}

}
