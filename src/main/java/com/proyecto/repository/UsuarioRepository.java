package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.enums.PerfilUsuario;
import com.proyecto.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	List<Usuario> findByPerfil(PerfilUsuario perfil);
	
	Usuario findByNombreUsuario(String nombreUsuario);
}
