package med.voll.api.medico;

public record DasdosListagemMedicos(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DasdosListagemMedicos(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
