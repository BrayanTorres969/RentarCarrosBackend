package com.fastandfuriousrent.configuration;

import com.fastandfuriousrent.enumeraciones.UserRole;
import com.fastandfuriousrent.services.jwt.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {  //Configuramos la seguridad de la aplicacion

    private final JwtAuthenticationFilter jwtAuthenticationFilter; //AUTENTICACION JWT
    private final UserService userService;  //SERIVICIO DE USUARIO

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) //desactivamos la proteccion contra CSRF(Cross-site Request Forgery).
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/auth/**").permitAll() //Permite todas las solicitudes a rutas que comienzan con /api/auth/**
                        .requestMatchers("/api/admin/**").hasAnyAuthority(UserRole.ADMIN.name()) //Todas las rutas que contengan ruteo Admin tambien tienen que tener la autoridad.
                        .requestMatchers("/api/customer/**").hasAnyAuthority(UserRole.CUSTOMER.name())//Todas las rutas que tengan ruteo customer tambien tienen que tener la autoridad Customer.
                        .anyRequest().authenticated() //Nos pedira una autenticación para cualquier otra solicitud
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//No se crean sesiones de usuario en el servidor.
                .authenticationProvider(authenticationProvider())//EL PROOVEDOR DE AUTENTICACION SERA PERSONALIZADO.
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);//SE AÑADE LA AUTENTICACION JWT ANTES DE USUARIO Y CONTRASEÑA.
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } //CODIFICAMOS LAS CONTRASEÑAS CON BCryptPasswordEncoder

    @Bean
    public AuthenticationProvider authenticationProvider() { //METODO QUE CONFIGURA UN PROOVEDOR DE AUTENTICACION.
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(); //Crea una nueva instancia.
        authProvider.setUserDetailsService(userService.userDetailsService());   //Con este servicio bucamos el usuario en la base de datos.
        authProvider.setPasswordEncoder(passwordEncoder());//Establece el codificador de contraseñas.
        return authProvider; //devuelve su instancia DaoAuthenticationProvider configurada.
    }

    @Bean//METODO QUE RECIBE LA CONFIGURACION DE AUTENTICACION DE SPRING SECURITY Y RETORNA EL METODO CONGIGURADO.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

