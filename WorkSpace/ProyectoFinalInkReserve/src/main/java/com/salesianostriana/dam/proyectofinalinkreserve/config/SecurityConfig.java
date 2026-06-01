package com.salesianostriana.dam.proyectofinalinkreserve.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(auth -> auth
        	    .requestMatchers("/login", "/css/**", "/js/**", "/images/**", "/h2-console/**").permitAll()
        	    .requestMatchers("/cambiar-contrasena").authenticated()

        	    // Solo ADMIN
        	    .requestMatchers(HttpMethod.GET, "/Dashboard").hasRole("ADMIN")
        	    .requestMatchers(HttpMethod.GET, "/Dashboard/Artistas/Eliminar/**").hasRole("ADMIN")
        	    .requestMatchers(HttpMethod.POST, "/nuevoArtistaCompleto").hasRole("ADMIN")
        	    .requestMatchers(HttpMethod.POST, "/Dashboard/Artistas/Editar/submit").hasRole("ADMIN")

        	    // ADMIN y USER
        	    .requestMatchers("/Dashboard/**").hasAnyRole("ADMIN", "USER")
        	    .requestMatchers(HttpMethod.POST, "/nuevoClienteCompleto").hasAnyRole("ADMIN", "USER")
        	    .requestMatchers(HttpMethod.POST, "/Dashboard/Clientes/Editar/submit").hasAnyRole("ADMIN", "USER")
        	    .requestMatchers(HttpMethod.POST, "/nuevaCitaCompleta").hasAnyRole("ADMIN", "USER")
        	    .requestMatchers(HttpMethod.POST, "/Dashboard/Citas/Editar/submit").hasAnyRole("ADMIN", "USER")
        	    .requestMatchers(HttpMethod.POST, "/nuevoTatuajeCompleto").hasAnyRole("ADMIN", "USER")
        	    .requestMatchers(HttpMethod.POST, "/Dashboard/Tatuajes/Editar/submit").hasAnyRole("ADMIN", "USER")

        	    .anyRequest().authenticated()
        	)
            
            .formLogin(form -> form
        	    .loginPage("/login")
        	    .successHandler((request, response, authentication) -> {
        	        boolean esAdmin = authentication.getAuthorities().stream()
        	            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        	        if (esAdmin) {
        	            response.sendRedirect("/Dashboard");
        	        } else {
        	            response.sendRedirect("/Dashboard/Artistas");
        	        }
        	    })
        	    .failureUrl("/login?error")
        	    .permitAll()
        	)
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            )
            .exceptionHandling(ex -> ex
            	    .accessDeniedPage("/acceso-denegado")
            );

        return http.build();
    }
	
	
    
	
}
