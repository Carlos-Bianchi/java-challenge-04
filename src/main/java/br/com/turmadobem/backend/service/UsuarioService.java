package br.com.turmadobem.backend.service;

import br.com.turmadobem.backend.dto.UsuarioRequest;
import br.com.turmadobem.backend.exception.BusinessException;
import br.com.turmadobem.backend.model.DentistaVoluntario;
import br.com.turmadobem.backend.model.EnderecoUsuario;
import br.com.turmadobem.backend.model.Especialidade;
import br.com.turmadobem.backend.model.Paciente;
import br.com.turmadobem.backend.model.PapelUsuario;
import br.com.turmadobem.backend.model.PrioridadeClinica;
import br.com.turmadobem.backend.model.StatusUsuario;
import br.com.turmadobem.backend.model.Usuario;
import br.com.turmadobem.backend.repository.EnderecoRepository;
import br.com.turmadobem.backend.repository.EspecialidadeRepository;
import br.com.turmadobem.backend.repository.DentistaRepository;
import br.com.turmadobem.backend.repository.PacienteRepository;
import br.com.turmadobem.backend.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class UsuarioService {
    private final UsuarioRepository usuarios;
    private final EnderecoRepository enderecos;
    private final EspecialidadeRepository especialidades;
    private final PacienteRepository pacientes;
    private final DentistaRepository dentistas;
    private final PasswordService passwords;

    public UsuarioService(UsuarioRepository usuarios, EnderecoRepository enderecos, EspecialidadeRepository especialidades,
                          PacienteRepository pacientes, DentistaRepository dentistas, PasswordService passwords) {
        this.usuarios = usuarios;
        this.enderecos = enderecos;
        this.especialidades = especialidades;
        this.pacientes = pacientes;
        this.dentistas = dentistas;
        this.passwords = passwords;
    }

    public List<Usuario> listAll() {
        return usuarios.listAll();
    }

    public Usuario findById(Long id) {
        return usuarios.findById(id);
    }

    @Transactional
    public Usuario registerUser(UsuarioRequest request) {
        if (usuarios.existsByEmail(request.email())) {
            throw new BusinessException(Response.Status.CONFLICT, "duplicate_email", "Email já cadastrado");
        }
        if (usuarios.existsByCpf(request.cpf())) {
            throw new BusinessException(Response.Status.CONFLICT, "duplicate_cpf", "CPF já cadastrado");
        }
        Usuario usuario = new Usuario();
        applyUserFields(usuario, request);
        usuario.senhaHash = passwords.hash(request.senha());
        usuario.status = request.status() == null ? StatusUsuario.pendente : request.status();
        usuarios.save(usuario);
        createAddressIfPresent(usuario, request);
        createRoleRecord(usuario, request);
        return usuario;
    }

    @Transactional
    public Usuario update(Long id, UsuarioRequest request) {
        Usuario usuario = usuarios.findById(id);
        applyUserFields(usuario, request);
        if (request.senha() != null && !request.senha().isBlank()) {
            usuario.senhaHash = passwords.hash(request.senha());
        }
        return usuario;
    }

    @Transactional
    public Usuario updateStatus(Long id, StatusUsuario status) {
        Usuario usuario = usuarios.findById(id);
        usuario.status = status;
        return usuario;
    }

    @Transactional
    public void delete(Long id) {
        usuarios.delete(usuarios.findById(id));
    }

    private void applyUserFields(Usuario usuario, UsuarioRequest request) {
        usuario.nomeCompleto = request.nomeCompleto();
        usuario.email = request.email();
        usuario.papel = request.papel();
        usuario.telefone = request.telefone();
        usuario.cpf = request.cpf();
        usuario.dataNascimento = request.dataNascimento();
        if (request.status() != null) usuario.status = request.status();
    }

    private void createAddressIfPresent(Usuario usuario, UsuarioRequest request) {
        if (request.endereco() == null) return;
        EnderecoUsuario endereco = new EnderecoUsuario();
        endereco.usuario = usuario;
        endereco.cep = request.endereco().cep();
        endereco.logradouro = request.endereco().logradouro();
        endereco.numero = request.endereco().numero();
        endereco.complemento = request.endereco().complemento();
        endereco.bairro = request.endereco().bairro();
        endereco.cidade = request.endereco().cidade();
        endereco.estado = request.endereco().estado();
        enderecos.save(endereco);
    }

    private void createRoleRecord(Usuario usuario, UsuarioRequest request) {
        if (usuario.papel == PapelUsuario.paciente) {
            if (request.especialidadeId() == null) {
                throw new BusinessException(Response.Status.BAD_REQUEST, "missing_specialty", "Especialidade é obrigatória para pacientes");
            }
            if (request.descricaoNecessidade() == null || request.descricaoNecessidade().isBlank()) {
                throw new BusinessException(Response.Status.BAD_REQUEST, "missing_need_description", "Descrição da necessidade é obrigatória para pacientes");
            }
            Paciente paciente = new Paciente();
            paciente.usuario = usuario;
            paciente.prioridade = request.prioridade() == null ? PrioridadeClinica.media : request.prioridade();
            paciente.descricaoNecessidade = request.descricaoNecessidade();
            paciente.aceitaDeslocamento = request.aceitaDeslocamento() == null || request.aceitaDeslocamento();
            paciente.ativoNaFila = true;
            paciente.especialidadeNecessaria = especialidades.findById(request.especialidadeId());
            pacientes.save(paciente);
        } else if (usuario.papel == PapelUsuario.dentista_voluntario) {
            if (request.cro() == null || request.cro().isBlank()) {
                throw new BusinessException(Response.Status.BAD_REQUEST, "missing_cro", "CRO é obrigatório para dentistas");
            }
            if (request.especialidadeId() == null) {
                throw new BusinessException(Response.Status.BAD_REQUEST, "missing_specialty", "Especialidade é obrigatória para dentistas");
            }
            if (request.nomeClinica() == null || request.nomeClinica().isBlank()) {
                throw new BusinessException(Response.Status.BAD_REQUEST, "missing_clinic_name", "Nome da clínica é obrigatório para dentistas");
            }
            if (request.turnoPreferencial() == null) {
                throw new BusinessException(Response.Status.BAD_REQUEST, "missing_availability", "Turno preferencial é obrigatório para dentistas");
            }
            DentistaVoluntario dentista = new DentistaVoluntario();
            dentista.usuario = usuario;
            dentista.cro = request.cro();
            dentista.nomeClinica = request.nomeClinica();
            dentista.turnoPreferencial = request.turnoPreferencial();
            dentista.aceitaNovosPacientes = request.aceitaNovosPacientes() == null || request.aceitaNovosPacientes();
            dentista.notaDisponibilidade = request.notaDisponibilidade();
            dentista.especialidadePrincipal = especialidades.findById(request.especialidadeId());
            dentistas.save(dentista);
        }
    }
}
