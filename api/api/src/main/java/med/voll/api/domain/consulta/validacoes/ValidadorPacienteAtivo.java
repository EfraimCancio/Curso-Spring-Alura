package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoExeption;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidadorPacienteAtivo {

    PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {

        var pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

        if (!pacienteAtivo) {
            throw new ValidacaoExeption("A consulta não pode ser agendada. O paciente não está ativo no sistema.");
        }
    }
}
