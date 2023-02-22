package com.proyecto.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.model.Usuario;
import com.proyecto.service.UsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService us;
	
	@GetMapping("/usuarios/{page}")
	public ResponseEntity<?> obtenerUsuarios(@PathVariable int page){
		Page<Usuario> usuarios = us.obtenerUsuarios(page);
		if(usuarios != null) {
			return ResponseEntity.ok(usuarios);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagina no existe");
		}
	}
	
	@PostMapping("usuarios/create")
	public ResponseEntity<?> crearUsuarios(@RequestBody Usuario usuario){
		Usuario usuarioCreado = us.crearUsuario(usuario);
		if(usuarioCreado != null) {
			return ResponseEntity.ok(usuarioCreado);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No fue posible crear el usuario: "+usuarioCreado);
		}
	}
	
	@GetMapping("usuarios/{nombreUsuario}")
	public ResponseEntity<?> buscarUsuario(@PathVariable String nombreUsuario){
		Usuario busqueda = us.buscarUsuario(nombreUsuario);
		if(busqueda != null) {
			return ResponseEntity.ok(busqueda);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario no encontrado");
		}
	}
	
}
