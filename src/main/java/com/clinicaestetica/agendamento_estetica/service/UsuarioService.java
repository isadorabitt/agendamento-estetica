package com.clinicaestetica.agendamento_estetica.service;


import com.clinicaestetica.agendamento_estetica.model.Usuario;
import com.clinicaestetica.agendamento_estetica.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void criarUsuario(String email, String nome, String senha) {
        String senhaCodificada = passwordEncoder.encode(senha); // Codifica a senha
        Usuario usuario = new Usuario(email, nome, senhaCodificada);
        usuarioRepository.save(usuario);
    }
}
