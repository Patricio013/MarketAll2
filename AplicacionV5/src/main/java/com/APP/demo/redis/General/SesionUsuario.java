package com.APP.demo.redis.General;

import java.io.Serializable;

public class SesionUsuario implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre;
    private String direccion;
    private String documentoIdentidad;
    private long inicioSesion;
    private long ultimaActividad;
    
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}
	public long getInicioSesion() {
		return inicioSesion;
	}
	public void setInicioSesion(long inicioSesion) {
		this.inicioSesion = inicioSesion;
	}
	public long getUltimaActividad() {
		return ultimaActividad;
	}
	public void setUltimaActividad(long ultimaActividad) {
		this.ultimaActividad = ultimaActividad;
	}
    
}
