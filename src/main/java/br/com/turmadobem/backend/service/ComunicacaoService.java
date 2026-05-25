package br.com.turmadobem.backend.service;

import br.com.turmadobem.backend.dto.ComunicacaoRequest;
import br.com.turmadobem.backend.model.Comunicacao;
import br.com.turmadobem.backend.repository.ComunicacaoRepository;
import br.com.turmadobem.backend.repository.MatchRepository;
import br.com.turmadobem.backend.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ComunicacaoService {
    private final ComunicacaoRepository comunicacoes;
    private final MatchRepository matches;
    private final UsuarioRepository usuarios;

    public ComunicacaoService(ComunicacaoRepository comunicacoes, MatchRepository matches, UsuarioRepository usuarios) {
        this.comunicacoes = comunicacoes;
        this.matches = matches;
        this.usuarios = usuarios;
    }

    public List<Comunicacao> listAll() { return comunicacoes.listAll(); }
    public Comunicacao findById(Long id) { return comunicacoes.findById(id); }

    @Transactional
    public Comunicacao create(ComunicacaoRequest request) {
        Comunicacao comunicacao = new Comunicacao();
        comunicacao.match = request.matchId() == null ? null : matches.findById(request.matchId());
        comunicacao.remetente = request.remetenteUsuarioId() == null ? null : usuarios.findById(request.remetenteUsuarioId());
        comunicacao.canal = request.canal();
        comunicacao.categoria = request.categoria();
        comunicacao.assunto = request.assunto();
        comunicacao.resumo = request.resumo();
        comunicacao.conteudo = request.conteudo();
        comunicacao.urgente = request.urgente() != null && request.urgente();
        comunicacao.lida = false;
        return comunicacoes.save(comunicacao);
    }

    @Transactional
    public Comunicacao markRead(Long id) {
        Comunicacao comunicacao = comunicacoes.findById(id);
        comunicacao.lida = true;
        return comunicacao;
    }

    @Transactional
    public void delete(Long id) { comunicacoes.delete(comunicacoes.findById(id)); }
}
