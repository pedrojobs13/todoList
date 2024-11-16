package com.fattocs.tarefa.integration.repository;

import com.fattocs.tarefa.exception.BusinessException;
import com.fattocs.tarefa.integration.entity.Tarefa;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface TarefaRepository {
    void deleteTarefa(Long id);

    void adicionarTarefa(String nome, Double custo, LocalDate data, Integer ordem) throws BusinessException;

    Collection<Tarefa> listagem();

    void updateTarefa(Tarefa update);

    Integer countTarefas();

    Tarefa getLastTarefa();

    Optional<Tarefa> getTarefaById(Long id);

    Optional<Tarefa> getTarefaSobre(Integer order);

    Optional<Tarefa> getTarefaAbaixo(Integer order);

    void updateOrder(Tarefa tarefaAtual, Tarefa tarefaAcima);


}
