package com.clinicaestetica.agendamento_estetica.controller;

import com.clinicaestetica.agendamento_estetica.dto.LoginRequest;
import com.clinicaestetica.agendamento_estetica.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("LoginRequest recebido: " + loginRequest.toString()); // Adicione esta linha
        if (loginRequest.getEmail() == null || loginRequest.getSenha() == null ||
                loginRequest.getEmail().isEmpty() || loginRequest.getSenha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email e senha são obrigatórios.");
        }
        try {
            System.out.println("Tentativa de login: " + loginRequest.getEmail());
            System.out.println("Senha fornecida: " + loginRequest.getSenha());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha())
            );
            System.out.println("Autenticação bem-sucedida para: " + authentication.getName());
            String token = jwtUtil.generateToken(authentication.getName());
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            System.out.println("Credenciais inválidas para: " + loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }
}