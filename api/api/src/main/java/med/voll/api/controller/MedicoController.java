package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("medicos")
@RestController
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrarMedicos(@RequestBody @Valid DadosCadastroMedico dados) {
        medicoRepository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DasdosListagemMedicos> listarMedicos(@PageableDefault(size=10, sort="name") Pageable paginacao) {

        return medicoRepository.findAllByAtivoTrue(paginacao).map(DasdosListagemMedicos::new);
    }

    @PutMapping
    @Transactional
    public void atualizarMedico(@RequestBody @Valid DadosAlterarMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping( value = "/{id}")
    @Transactional
    public void excluirMedico(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.inativar();
    }
}
