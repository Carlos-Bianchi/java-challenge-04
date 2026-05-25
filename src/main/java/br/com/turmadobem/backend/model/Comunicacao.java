package br.com.turmadobem.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "comunicacoes")
public class Comunicacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    public Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "remetente_usuario_id")
    public Usuario remetente;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(nullable = false, columnDefinition = "canal_comunicacao")
    public CanalComunicacao canal;

    @Column(nullable = false, length = 80)
    public String categoria;

    @Column(nullable = false, length = 180)
    public String assunto;

    @Column(nullable = false, length = 255)
    public String resumo;

    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    @Column(nullable = false, columnDefinition = "text")
    public String conteudo;

    @Column(nullable = false)
    public Boolean urgente;

    @Column(nullable = false)
    public Boolean lida;

    @Column(name = "enviada_em", insertable = false, updatable = false)
    public LocalDateTime enviadaEm;
}
