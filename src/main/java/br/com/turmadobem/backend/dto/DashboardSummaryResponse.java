package br.com.turmadobem.backend.dto;

import java.util.Map;

public record DashboardSummaryResponse(long totalUsuarios, Map<String, Long> usuariosPorPapel, long matchesConfirmados,
                                       long comunicacoesNaoLidas, Map<String, Long> agendamentosPorStatus) {
}
