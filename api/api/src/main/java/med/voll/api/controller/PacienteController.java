package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping("pacientes")
@RestController
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dados) {
        pacienteRepository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPacientes> listarPacientes(@PageableDefault(size=10, sort="name") Pageable paginacao){
        return pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPacientes::new);
    }

    @PutMapping
    @Transactional
    public void atualizarPaciente(@RequestBody @Valid DadosAlterarPaciente dados) {
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public void excluirPaciente(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();
    }
}
