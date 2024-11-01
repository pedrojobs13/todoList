package com.fattocs.tarefa.business.listagem.usecase;

import com.fattocs.tarefa.business.model.TarefaModel;
import com.fattocs.tarefa.integration.entity.Tarefa;
import com.fattocs.tarefa.integration.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ListagemUseCaseImpl implements ListagemUseCase {
    private final TarefaRepository tarefaRepository;

    @Override
    public Collection<TarefaModel> execute() {
        Collection<Tarefa> tarefas = tarefaRepository.listagem();

        return tarefas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }


    private TarefaModel toModel(Tarefa tarefa) {
        return TarefaModel.builder()
                .id(tarefa.getId())
                .nome(tarefa.getNome())
                .custo(tarefa.getCusto())
                .dataLimite(tarefa.getDataLimite())
                .orderApresentacao(tarefa.getOrderApresentacao())
                .build();
    }
}
