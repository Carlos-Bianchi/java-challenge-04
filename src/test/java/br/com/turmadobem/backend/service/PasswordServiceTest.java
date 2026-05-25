package br.com.turmadobem.backend.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PasswordServiceTest {
    @Test
    void hashesAndVerifiesPassword() {
        PasswordService service = new PasswordService();

        String hash = service.hash("segredo123");

        assertThat(hash).isNotEqualTo("segredo123");
        assertThat(service.verify("segredo123", hash)).isTrue();
        assertThat(service.verify("errada", hash)).isFalse();
    }
}
