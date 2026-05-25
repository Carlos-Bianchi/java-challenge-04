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
@Table(name = "pacientes")
public class Paciente {
    @Id
    @Column(name = "usuario_id")
    public Long usuarioId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "usuario_id")
    public Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidade_necessaria_id", nullable = false)
    public Especialidade especialidadeNecessaria;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(nullable = false, columnDefinition = "prioridade_clinica")
    public PrioridadeClinica prioridade;

    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    @Column(name = "descricao_necessidade", nullable = false, columnDefinition = "text")
    public String descricaoNecessidade;

    @Column(name = "aceita_deslocamento", nullable = false)
    public Boolean aceitaDeslocamento;

    @Column(name = "ativo_na_fila", nullable = false)
    public Boolean ativoNaFila;

    @Column(name = "criado_em", insertable = false, updatable = false)
    public LocalDateTime criadoEm;
}
