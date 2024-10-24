package com.isy2202.grupo1.config;

import org.springframework.security.core.userdetails.User;
import com.isy2202.grupo1.model.Usuario;
import com.isy2202.grupo1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public UserDetailsService userDetailsService() {
        return (String correo) -> usuarioService.findByCorreo(correo)
                .map((Usuario usuario) -> {
                    return User.builder()
                            .username(usuario.getCorreo())
                            .password(usuario.getPassword())
                            .roles(usuario.getRole())
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + correo));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/home", "/login", "/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/recetas/**").authenticated() 
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true) 
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutUrl("/logout") 
                .logoutSuccessUrl("/home") 
                .permitAll() 
                .invalidateHttpSession(true) 
                .clearAuthentication(true) 
            );
    
        return http.build();
    }

}
