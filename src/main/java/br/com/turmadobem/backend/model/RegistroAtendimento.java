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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "registros_atendimento")
public class RegistroAtendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agendamento_id", nullable = false, unique = true)
    public Agendamento agendamento;

    @Column(name = "data_atendimento", nullable = false)
    public LocalDate dataAtendimento;

    @Column(nullable = false, length = 120)
    public String procedimento;

    @Column(name = "condicao_paciente", nullable = false, length = 120)
    public String condicaoPaciente;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(nullable = false, columnDefinition = "gravidade_condicao")
    public GravidadeCondicao gravidade;

    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    @Column(columnDefinition = "text")
    public String observacoes;

    @Column(name = "proxima_consulta")
    public LocalDate proximaConsulta;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(nullable = false, columnDefinition = "status_tratamento")
    public StatusTratamento status;

    @Column(name = "criado_em", insertable = false, updatable = false)
    public LocalDateTime criadoEm;
}
