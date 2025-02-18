package com.clinicaestetica.agendamento_estetica.service;

import com.clinicaestetica.agendamento_estetica.model.Usuario;
import com.clinicaestetica.agendamento_estetica.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Carregando usuário: {}", email);
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.warn("Usuário não encontrado: {}", email);
                    return new UsernameNotFoundException("Usuário não encontrado");
                });
        logger.info("Usuário encontrado: {}", usuario.getEmail());
        logger.debug("Senha no banco de dados: {}", usuario.getSenha());
        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getAuthorities()
        );
    }
}