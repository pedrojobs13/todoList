package com.fattocs.tarefa.business.busca.usecase;

import com.fattocs.tarefa.business.model.TarefaModel;
import com.fattocs.tarefa.exception.BusinessException;
import com.fattocs.tarefa.integration.entity.Tarefa;
import com.fattocs.tarefa.integration.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTarefaImpl implements GetTarefa {
    private final TarefaRepository tarefaRepository;

    @Override
    public TarefaModel execute(Long id) throws BusinessException {
        return tarefaRepository.getTarefaById(id)
                .map(this::toModel)
                .orElseThrow(() -> new BusinessException("Tarefa não encontrada"));
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
