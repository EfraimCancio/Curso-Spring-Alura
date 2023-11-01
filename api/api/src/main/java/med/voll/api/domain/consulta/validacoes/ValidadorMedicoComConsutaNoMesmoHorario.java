package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoExeption;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorMedicoComConsutaNoMesmoHorario {

    ConsultaRepository consultaRepository;

    public  void validar(DadosAgendamentoConsulta dados) {

        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoExeption("O Médico ja possui consulta agendada para esse horário");
        }
    }
}
