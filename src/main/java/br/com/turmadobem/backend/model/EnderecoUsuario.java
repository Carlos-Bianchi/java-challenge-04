package br.com.turmadobem.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "enderecos_usuarios")
public class EnderecoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    public Usuario usuario;

    @Column(nullable = false, columnDefinition = "char(8)")
    public String cep;

    @Column(nullable = false, length = 150)
    public String logradouro;

    @Column(nullable = false, length = 20)
    public String numero;

    @Column(length = 100)
    public String complemento;

    @Column(nullable = false, length = 100)
    public String bairro;

    @Column(nullable = false, length = 100)
    public String cidade;

    @Column(nullable = false, columnDefinition = "char(2)")
    public String estado;
}
