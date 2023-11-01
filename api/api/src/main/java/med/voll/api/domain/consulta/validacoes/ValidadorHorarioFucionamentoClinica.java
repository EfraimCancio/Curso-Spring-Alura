package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoExeption;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.DayOfWeek;

public class ValidadorHorarioFucionamentoClinica {
    public void vailidar(DadosAgendamentoConsulta dados) {

        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturadaClinica = dataConsulta.getHour() < 7;
        var depoisDoFechamentoDaClinica = dataConsulta.getHour() > 18;

        if (domingo || antesDaAberturadaClinica || depoisDoFechamentoDaClinica) {
            throw new ValidacaoExeption("O horário ou a dia da consulta é inválido.");
        }
    }
}
