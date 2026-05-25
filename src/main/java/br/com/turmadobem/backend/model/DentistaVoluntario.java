package br.com.turmadobem.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "dentistas_voluntarios")
public class DentistaVoluntario {
    @Id
    @Column(name = "usuario_id")
    public Long usuarioId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "usuario_id")
    public Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidade_principal_id", nullable = false)
    public Especialidade especialidadePrincipal;

    @Column(nullable = false, unique = true, length = 20)
    public String cro;

    @Column(name = "nome_clinica", nullable = false, length = 150)
    public String nomeClinica;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "turno_preferencial", nullable = false, columnDefinition = "turno_disponibilidade")
    public TurnoDisponibilidade turnoPreferencial;

    @Column(name = "aceita_novos_pacientes", nullable = false)
    public Boolean aceitaNovosPacientes;

    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    @Column(name = "nota_disponibilidade", columnDefinition = "text")
    public String notaDisponibilidade;

    @Column(name = "criado_em", insertable = false, updatable = false)
    public LocalDateTime criadoEm;
}
