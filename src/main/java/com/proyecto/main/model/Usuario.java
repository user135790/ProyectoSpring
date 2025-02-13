package com.proyecto.main.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.proyecto.main.enums.PerfilUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull(message = "campo nombre no puede ser nulo")
	@NotBlank(message = "campo nombre no puede solo un espacio")
	@Column(name = "nombre_usuario", unique = true, nullable = false)
	private String nombre;
	
	@NotNull(message = "campo correo electronico no puede ser nulo")
	@NotBlank(message = "campo correo electronico no puede solo un espacio")
	@Email(message = "campo correo electronico debe ser un correo valido")
	@Column(name = "correo_electronico", nullable = false)
	private String correoElectronico;
	
	@NotNull(message = "campo contrasena no puede ser nulo")
	@NotBlank(message = "campo contrasena no puede solo un espacio")
	@Size(min = 8, message = "campo contrasena debe tener al menos 8 caracteres")
	@Column(name = "contrasena_usuario", nullable = false)
	@Pattern(regexp = "(?=.*[A-Z])(.*\\d.*)",message = "Al menos debe haber una mayuscula y una minuscula")
	private String contrasena;
	
	@NotNull(message = "campo no puede ser nulo")
	@Column(name = "perfil_usuario", nullable = false)
	@Enumerated(EnumType.STRING)
	private PerfilUsuario perfil;

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public PerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> array = new ArrayList<>();
		GrantedAuthority rol = new SimpleGrantedAuthority(this.getPerfil().toString());
		array.add(rol);
		return array;
    }

    @Override
    public String getPassword() {
        return this.getContrasena();
    }

    @Override
    public String getUsername() {
    	return this.nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return  true;
    }
	
	
}
