package com.proyecto.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.main.model.Usuario;
import com.proyecto.main.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository ur;
	
	public Usuario crearUsuario(Usuario usuario){
		return ur.save(usuario);
	}
	
	public Usuario buscarUsuario(String nombreUsuario) {
		return ur.findByNombre(nombreUsuario);
	}
	
	public Page<Usuario> obtenerUsuarios(int page){
		return ur.findAll(Pageable.ofSize(3).withPage(page));
	}
}