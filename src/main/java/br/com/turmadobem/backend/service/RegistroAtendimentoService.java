package br.com.turmadobem.backend.service;

import br.com.turmadobem.backend.dto.RegistroAtendimentoRequest;
import br.com.turmadobem.backend.exception.BusinessException;
import br.com.turmadobem.backend.model.Agendamento;
import br.com.turmadobem.backend.model.RegistroAtendimento;
import br.com.turmadobem.backend.model.StatusAgendamento;
import br.com.turmadobem.backend.model.StatusMatch;
import br.com.turmadobem.backend.model.StatusTratamento;
import br.com.turmadobem.backend.repository.AgendamentoRepository;
import br.com.turmadobem.backend.repository.RegistroAtendimentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class RegistroAtendimentoService {
    private final RegistroAtendimentoRepository registros;
    private final AgendamentoRepository agendamentos;

    public RegistroAtendimentoService(RegistroAtendimentoRepository registros, AgendamentoRepository agendamentos) {
        this.registros = registros;
        this.agendamentos = agendamentos;
    }

    public List<RegistroAtendimento> listAll() { return registros.listAll(); }
    public RegistroAtendimento findById(Long id) { return registros.findById(id); }

    @Transactional
    public RegistroAtendimento recordAttendance(RegistroAtendimentoRequest request) {
        RegistroAtendimento registro = new RegistroAtendimento();
        apply(registro, request);
        registro.agendamento.status = StatusAgendamento.concluida;
        return registros.save(registro);
    }

    @Transactional
    public RegistroAtendimento update(Long id, RegistroAtendimentoRequest request) {
        RegistroAtendimento registro = registros.findById(id);
        apply(registro, request);
        return registro;
    }

    @Transactional
    public void delete(Long id) { registros.delete(registros.findById(id)); }

    private void apply(RegistroAtendimento registro, RegistroAtendimentoRequest request) {
        Agendamento agendamento = agendamentos.findById(request.agendamentoId());
        if (agendamento.match.status != StatusMatch.confirmado) {
            throw new BusinessException(Response.Status.CONFLICT, "match_not_confirmed", "Atendimento exige match confirmado");
        }
        if (agendamento.status == StatusAgendamento.cancelada) {
            throw new BusinessException(Response.Status.CONFLICT, "appointment_cancelled", "Agendamento cancelado não pode receber atendimento");
        }
        registro.agendamento = agendamento;
        registro.dataAtendimento = request.dataAtendimento();
        registro.procedimento = request.procedimento();
        registro.condicaoPaciente = request.condicaoPaciente();
        registro.gravidade = request.gravidade();
        registro.observacoes = request.observacoes();
        registro.proximaConsulta = request.proximaConsulta();
        registro.status = request.status() == null ? StatusTratamento.em_tratamento : request.status();
    }
}
