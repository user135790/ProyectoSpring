package com.proyecto.main.dto;

import java.io.Serializable;

public class SesionDTO implements Serializable{

	private String nombre;
	private String contrasena;
	
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
