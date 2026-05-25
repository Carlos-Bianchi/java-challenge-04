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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_usuario_id", nullable = false)
    public Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dentista_usuario_id", nullable = false)
    public DentistaVoluntario dentista;

    @Column(name = "percentual_compatibilidade", nullable = false, precision = 5, scale = 2)
    public BigDecimal percentualCompatibilidade;

    @Column(name = "pontuacao_localizacao", nullable = false)
    public Short pontuacaoLocalizacao;

    @Column(name = "pontuacao_especialidade", nullable = false)
    public Short pontuacaoEspecialidade;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(nullable = false, columnDefinition = "status_match")
    public StatusMatch status;

    @Column(name = "solicitado_em", nullable = false, updatable = false)
    public LocalDateTime solicitadoEm;

    @Column(name = "respondido_em")
    public LocalDateTime respondidoEm;

    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    @Column(columnDefinition = "text")
    public String observacoes;
}
