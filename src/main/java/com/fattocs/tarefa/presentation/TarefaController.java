package com.fattocs.tarefa.presentation;

import com.fattocs.tarefa.business.busca.usecase.GetTarefa;
import com.fattocs.tarefa.business.editar.usecase.EditarTarefaUseCase;
import com.fattocs.tarefa.business.excluir.usecase.ExcluirTarefaUseCase;
import com.fattocs.tarefa.business.incluir.usecase.IncluirTarefaUseCase;
import com.fattocs.tarefa.business.listagem.usecase.ListagemUseCase;
import com.fattocs.tarefa.business.model.TarefaModel;
import com.fattocs.tarefa.business.ordem.usecase.EditarOrdemDownUseCase;
import com.fattocs.tarefa.business.ordem.usecase.EditarOrdemUpUseCase;
import com.fattocs.tarefa.exception.BusinessException;
import com.fattocs.tarefa.presentation.dto.Request;
import com.fattocs.tarefa.presentation.validation.ApiValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/v1/api/tarefa")
@RequiredArgsConstructor
@Log4j2
public class TarefaController {
    private final ExcluirTarefaUseCase excluirTarefaUseCase;
    private final EditarTarefaUseCase editarTarefaUseCase;
    private final IncluirTarefaUseCase incluirTarefaUseCase;
    private final ListagemUseCase listagemUseCase;
    private final EditarOrdemUpUseCase editarOrdemUpUseCase;
    private final EditarOrdemDownUseCase editarOrdemDownUseCase;
    private final GetTarefa getTarefa;

    @PostMapping()
    public ResponseEntity<?> adicionarTarefa(@RequestBody Request request) throws BusinessException {
        ResponseEntity<String> body = ApiValidation.validateApi(request);
        if (body != null) return body;
        incluirTarefaUseCase.execute(request.getNome(), request.getCusto(), request.getDataLimite());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editarTarefa(@PathVariable Long id, @RequestBody Request request) {
        editarTarefaUseCase.execute(id, request.getNome(), request.getCusto(), request.getDataLimite());
    }

    @PutMapping("ordemUp/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editarOrderUp(@PathVariable Long id) {
        editarOrdemUpUseCase.execute(id);
    }

    @PutMapping("ordemDown/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editarOrderDown(@PathVariable Long id) {

        editarOrdemDownUseCase.execute(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirTarefa(@PathVariable Long id) {
        excluirTarefaUseCase.execute(id);
    }

    @GetMapping()
    public Collection<TarefaModel> listarTarefas() {
        return listagemUseCase.execute();
    }

    @GetMapping("/{id}")
    public TarefaModel buscarTarefa(@PathVariable Long id) throws BusinessException {
        return getTarefa.execute(id);
    }
}