package com.fattocs.tarefa.business.editar.usecase;

import com.fattocs.tarefa.integration.entity.Tarefa;
import com.fattocs.tarefa.integration.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class EditarTarefaUseCaseImpl implements EditarTarefaUseCase {
    private final TarefaRepository tarefaRepository;

    @Override
    public void execute(Long id, String nome, Double custo, LocalDate dataLimite) {
        Tarefa tarefa = Tarefa.builder()
                .id(id)
                .nome(nome)
                .custo(custo)
                .dataLimite(dataLimite)
                .build();
        tarefaRepository.updateTarefa(tarefa);
    }
}
