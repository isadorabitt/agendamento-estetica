package com.clinicaestetica.agendamento_estetica.temporario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorBCrypt {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Gere hashes BCrypt para as senhas
        System.out.println("Hash para '123456': " + encoder.encode("123456"));
    }
}
