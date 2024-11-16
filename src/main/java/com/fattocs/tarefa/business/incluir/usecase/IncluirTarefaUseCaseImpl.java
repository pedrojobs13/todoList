package com.fattocs.tarefa.business.incluir.usecase;

import com.fattocs.tarefa.exception.BusinessException;
import com.fattocs.tarefa.integration.entity.Tarefa;
import com.fattocs.tarefa.integration.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Log4j2
public class IncluirTarefaUseCaseImpl implements IncluirTarefaUseCase {
    private final TarefaRepository tarefaRepository;

    @Override
    public void execute(String nome, Double custo, LocalDate data) throws BusinessException {
        if (tarefaRepository.countTarefas() == 0) {
            tarefaRepository.adicionarTarefa(nome, custo, data, 1);
            return;
        }

        Integer ordem = tarefaRepository.getLastTarefa().getOrderApresentacao() + 1;

        tarefaRepository.adicionarTarefa(nome, custo, data, ordem);

    }
}
