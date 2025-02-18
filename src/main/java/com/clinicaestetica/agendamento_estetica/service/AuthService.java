package com.clinicaestetica.agendamento_estetica.service;

import com.clinicaestetica.agendamento_estetica.dto.LoginRequest;
import com.clinicaestetica.agendamento_estetica.model.Usuario;
import com.clinicaestetica.agendamento_estetica.repository.UsuarioRepository;
import com.clinicaestetica.agendamento_estetica.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsuarioRepository usuarioRepository, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public String authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return jwtUtil.generateToken(usuario.getEmail());
    }
}