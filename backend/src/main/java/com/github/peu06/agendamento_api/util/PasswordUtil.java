package com.github.peu06.agendamento_api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashSenha(String senha){
        return encoder.encode(senha);
    }

    public static boolean verificarSenha(String senhaDigitada, String senhaHash){
        return encoder.matches(senhaDigitada, senhaHash);
    }
}
