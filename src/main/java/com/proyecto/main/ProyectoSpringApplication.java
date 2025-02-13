package com.proyecto.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.proyecto.main.enums.PerfilUsuario;
import com.proyecto.main.model.Usuario;
import com.proyecto.main.service.UsuarioService;

@SpringBootApplication
public class ProyectoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoSpringApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UsuarioService service) {
		return (args) -> {
			// save a few customers
		Usuario usuario = new Usuario();
		usuario.setNombre("admin");
		usuario.setContrasena("Admin123");
		usuario.setCorreoElectronico("admin@gmail.com");
		usuario.setPerfil(PerfilUsuario.ADMINISTRADOR);
		service.crearUsuario(usuario);
		};
	}
}
