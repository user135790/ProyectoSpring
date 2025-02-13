package com.proyecto.main.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.proyecto.main.service.UsuarioService;


@Configuration
@EnableWebSecurity()
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain securityfilterChain(HttpSecurity http, AuthorizationFilter authFilter) throws Exception {
		return http.csrf(csrf-> {
					csrf.disable();
				})
				
				
				.authorizeHttpRequests(auth->{
                    try {
                        auth
                                .requestMatchers(HttpMethod.POST, "/usuarios/auth").permitAll()
                                .requestMatchers(HttpMethod.GET, "/usuarios/").authenticated()
                                .requestMatchers(HttpMethod.GET, "/usuarios/find/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/usuarios/create").authenticated()
                                .requestMatchers(HttpMethod.GET, "/usuarios/perfil").authenticated()
                                .and()
                                .logout(logout->{
                                    logout
                                            .clearAuthentication(true)
                                            .invalidateHttpSession(true)
                                            .logoutUrl("/usuarios/logout");
                                });
                    } catch (Exception ex) {
						System.out.println(ex.getMessage());
                    }
								
				})
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
				.build();

	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userService, PasswordEncoder passwordEncoder) throws Exception {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return new ProviderManager(authProvider);
	}

	@Bean
	public UserDetailsService userService(){
		return new UsuarioService() ;
	}

	@Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
    }
	
}
