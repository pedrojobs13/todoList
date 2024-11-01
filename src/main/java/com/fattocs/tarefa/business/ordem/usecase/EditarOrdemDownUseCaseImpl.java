package com.fattocs.tarefa.business.ordem.usecase;

import com.fattocs.tarefa.integration.entity.Tarefa;
import com.fattocs.tarefa.integration.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Log4j2
public class EditarOrdemDownUseCaseImpl implements EditarOrdemDownUseCase {
    private final TarefaRepository tarefaRepository;

    @Override
    public void execute(Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.getTarefaById(id);
        if (tarefa.isEmpty()) {
            log.error("Tarefa não encontrada");
        }

        Optional<Tarefa> tarefaDown = tarefaRepository.getTarefaAbaixo(tarefa.get().getOrderApresentacao());


        if (tarefaDown.isEmpty()) {
            log.error("Tarefa sobre não encontrada");
        }
        tarefaRepository.updateOrder(tarefa.get(), tarefaDown.get());
    }
}
