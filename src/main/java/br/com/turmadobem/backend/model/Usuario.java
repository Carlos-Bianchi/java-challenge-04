package br.com.turmadobem.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nome_completo", nullable = false, length = 150)
    public String nomeCompleto;

    @Column(nullable = false, unique = true, length = 255)
    public String email;

    @Column(name = "senha_hash", nullable = false)
    public String senhaHash;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(nullable = false, columnDefinition = "papel_usuario")
    public PapelUsuario papel;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(nullable = false, columnDefinition = "status_usuario")
    public StatusUsuario status;

    @Column(length = 20)
    public String telefone;

    @Column(nullable = false, unique = true, columnDefinition = "char(11)")
    public String cpf;

    @Column(name = "data_nascimento", nullable = false)
    public LocalDate dataNascimento;

    @Column(name = "criado_em", insertable = false, updatable = false)
    public LocalDateTime criadoEm;

    @Column(name = "atualizado_em", insertable = false, updatable = false)
    public LocalDateTime atualizadoEm;
}
