package br.com.turmadobem.backend.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordService {
    public String hash(String plainText) {
        return BCrypt.withDefaults().hashToString(12, plainText.toCharArray());
    }

    public boolean verify(String plainText, String hash) {
        return BCrypt.verifyer().verify(plainText.toCharArray(), hash).verified;
    }
}
