package com.clinicaestetica.agendamento_estetica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/usuarios").permitAll() // Permite acesso irrestrito a /usuarios
                        .anyRequest().authenticated() // Requer autenticação para todas as outras requisições
                )
                .httpBasic(basic -> basic.disable()) // Desabilita a autenticação básica
                .csrf(csrf -> csrf.disable()); // Desabilita a proteção CSRF (se necessário)
        return http.build();
    }
}