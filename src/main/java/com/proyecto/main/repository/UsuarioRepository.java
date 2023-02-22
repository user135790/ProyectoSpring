package com.proyecto.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.main.enums.PerfilUsuario;
import com.proyecto.main.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	List<Usuario> findByPerfil(PerfilUsuario perfil);
	
	Usuario findByNombre(String nombreUsuario);
}
