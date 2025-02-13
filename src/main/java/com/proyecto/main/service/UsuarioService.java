package com.proyecto.main.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.main.enums.PerfilUsuario;
import com.proyecto.main.model.Usuario;
import com.proyecto.main.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	@Autowired
	private UsuarioRepository ur;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Usuario crearUsuario(Usuario usuario){
		String contraseñaEncriptada = passwordEncoder.encode(usuario.getContrasena()); 
		usuario.setContrasena(contraseñaEncriptada);
		return ur.save(usuario);
	}
	
	public Usuario buscarUsuario(String nombreUsuario) {
		return ur.findByNombre(nombreUsuario);
	}

	public Collection<GrantedAuthority> obtenerRol(String nombreUsuario){
		Collection<GrantedAuthority> rol = new ArrayList<>();
		SimpleGrantedAuthority permisos = new SimpleGrantedAuthority(ur.getPerfil(nombreUsuario));
		rol.add(permisos);
		
		return rol;
	}
	
	public List<Usuario> obtenerUsuarios(){
		return ur.findAll();
	}
	
	public List<Usuario> buscarPorPerfil(PerfilUsuario perfil){
		return ur.findByPerfil(perfil);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = ur.findByNombre(username);

		return User.builder().username(user.getUsername()).password(user.getPassword()).authorities(user.getAuthorities()).build();
	}
}
