package com.proyecto.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.main.enums.PerfilUsuario;
import com.proyecto.main.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	List<Usuario> findByPerfil(PerfilUsuario perfil);
	
	@Query("SELECT u FROM Usuario u WHERE ?1 = u.nombre")
	Usuario findByNombre(String nombreUsuario);
}
