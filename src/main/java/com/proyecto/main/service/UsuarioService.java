package com.proyecto.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.main.enums.PerfilUsuario;
import com.proyecto.main.model.Usuario;
import com.proyecto.main.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository ur;
	
	public Usuario crearUsuario(Usuario usuario){
		return ur.save(usuario);
	}
	
	public Usuario buscarUsuario(String nombreUsuario) {
		return ur.findByNombre(nombreUsuario);
	}
	
	public List<Usuario> obtenerUsuarios(){
		return ur.findAll();
	}
	
	public List<Usuario> buscarPorPerfil(PerfilUsuario perfil){
		return ur.findByPerfil(perfil);
	}

	public Usuario iniciarSesion(String usuario, String contrasena) {
		Usuario usuarioEncontrado=ur.findByNombre(usuario);
		if(usuarioEncontrado != null) {
			if(usuario.equals(usuarioEncontrado.getNombre()) && contrasena.equals(usuarioEncontrado.getContrasena())) {
				return usuarioEncontrado;
			}
		}
		return null;
	}
}
