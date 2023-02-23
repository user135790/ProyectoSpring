package com.proyecto.main.dto;

import java.io.Serializable;

import com.proyecto.main.enums.PerfilUsuario;
import com.proyecto.main.model.Usuario;

public class UsuarioDTO implements Serializable{
	
	private String nombre;
	private String correoElectronico;
	private PerfilUsuario perfil;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correo) {
		this.correoElectronico = correo;
	}
	public PerfilUsuario getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}
	
	public UsuarioDTO fromUsuario(Usuario usuario) {
		UsuarioDTO usuarioDto= new UsuarioDTO();
		if(usuario !=null) {
			usuarioDto.setNombre(usuario.getNombre());
			usuarioDto.setCorreoElectronico(usuario.getCorreoElectronico());
			usuarioDto.setPerfil(usuario.getPerfil());
			return usuarioDto;
		}
		return null;
	}
	
	

}
