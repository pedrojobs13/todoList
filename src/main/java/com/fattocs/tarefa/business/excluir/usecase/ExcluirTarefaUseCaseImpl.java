package com.fattocs.tarefa.business.excluir.usecase;

import com.fattocs.tarefa.integration.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExcluirTarefaUseCaseImpl implements ExcluirTarefaUseCase {
    private final TarefaRepository tarefaRepository;


    @Override
    public void execute(Long id) {
        tarefaRepository.deleteTarefa(id);
    }
}
