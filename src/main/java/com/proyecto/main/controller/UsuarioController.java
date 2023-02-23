package com.proyecto.main.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.main.dto.SesionDTO;
import com.proyecto.main.model.Usuario;
import com.proyecto.main.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("http://localhost:4200")
public class UsuarioController {

	@Autowired
	private UsuarioService us;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ArrayList<String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
		ArrayList<String> errors = new ArrayList<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String errorMessage = error.getDefaultMessage();
	        errors.add(errorMessage);
	    });
	    return errors;
	}
	
	@GetMapping("/{page}")
	public ResponseEntity<?> obtenerUsuarios(@PathVariable int page){
		Page<Usuario> usuarios = us.obtenerUsuarios(page);
		if(usuarios != null) {
			return ResponseEntity.ok(usuarios.get());
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagina no existe");
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> crearUsuarios(@Validated @RequestBody Usuario usuario){
		Usuario usuarioCreado = us.crearUsuario(usuario);
		if(usuarioCreado != null) {
			return ResponseEntity.ok(usuarioCreado);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No fue posible crear el usuario: "+usuarioCreado);
		}
	}
	
	@GetMapping("/find/{nombreUsuario}")
	public ResponseEntity<?> buscarUsuario(@PathVariable String nombreUsuario){
		Usuario busqueda = us.buscarUsuario(nombreUsuario);
		if(busqueda != null) {
			return ResponseEntity.ok(busqueda);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario no encontrado");
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> iniciarSesion(@RequestBody SesionDTO sesion){
		if(us.iniciarSesion(sesion.getNombre(), sesion.getContrasena())) {
			return ResponseEntity.ok("Inicio de sesion exitoso");
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso Denegado");
		}
	}
	
}
