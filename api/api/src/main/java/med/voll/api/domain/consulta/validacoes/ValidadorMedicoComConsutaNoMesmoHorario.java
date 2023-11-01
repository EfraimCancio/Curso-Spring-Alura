package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoExeption;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class ValidadorMedicoComConsutaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    ConsultaRepository consultaRepository;

    public  void validar(DadosAgendamentoConsulta dados) {

        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoExeption("O Médico ja possui consulta agendada para esse horário");
        }
    }
}
