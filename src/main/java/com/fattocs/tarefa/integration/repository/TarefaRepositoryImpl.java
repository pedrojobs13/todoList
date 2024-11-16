package com.fattocs.tarefa.integration.repository;

import com.fattocs.tarefa.exception.BusinessException;
import com.fattocs.tarefa.integration.entity.Tarefa;
import com.fattocs.tarefa.integration.entity.TarefaRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Log4j2
public class TarefaRepositoryImpl implements TarefaRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TarefaRowMapper tarefaRowMapper;

    @Override
    public void deleteTarefa(Long id) {
        var sql = """
                DELETE FROM tarefa
                WHERE id= ?;
                """;
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void adicionarTarefa(String nome, Double custo, LocalDate data, Integer ordem) throws BusinessException {
        var sql = """
                INSERT INTO tarefa (nome, custo, data_limite, ordem_de_apresentacao)
                VALUES (?, ?, ?, ?)
                """;
        try {
            jdbcTemplate.update(sql, nome, custo, data, ordem);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("O custo da tarefa não pode ser maior que 99.999.999");
        }
    }

    @Override
    public Collection<Tarefa> listagem() {
        var sql = """
                SELECT id, nome, custo, data_limite, ordem_de_apresentacao FROM tarefa order by ordem_de_apresentacao
                """;
        return jdbcTemplate.query(sql, tarefaRowMapper);
    }

    @Override
    public Tarefa getLastTarefa() {
        var sql = """ 
                SELECT id, nome, custo, data_limite, ordem_de_apresentacao
                FROM tarefa
                ORDER BY ordem_de_apresentacao DESC
                LIMIT 1 """;
        return jdbcTemplate.queryForObject(sql, tarefaRowMapper);
    }

    @Override
    public Optional<Tarefa> getTarefaById(Long id) {
        String sql = "SELECT id, nome, custo, data_limite, ordem_de_apresentacao FROM tarefa WHERE id = ?";
        return jdbcTemplate.query(sql, tarefaRowMapper, id).stream().findFirst();

    }

    @Override
    public Optional<Tarefa> getTarefaSobre(Integer order) {
        String sql = """
                SELECT id, nome, custo, data_limite, ordem_de_apresentacao 
                FROM tarefa 
                WHERE ordem_de_apresentacao < ? 
                ORDER BY ordem_de_apresentacao DESC 
                LIMIT 1
                """;
        return jdbcTemplate.query(sql, tarefaRowMapper, order).stream().findFirst();
    }

    @Override
    public Optional<Tarefa> getTarefaAbaixo(Integer order) {
        String sql = """
                SELECT id, nome, custo, data_limite, ordem_de_apresentacao 
                FROM tarefa 
                WHERE ordem_de_apresentacao > ? 
                ORDER BY ordem_de_apresentacao ASC 
                LIMIT 1
                """;
        return jdbcTemplate.query(sql, tarefaRowMapper, order).stream().findFirst();
    }


    public void updateOrder(Tarefa tarefaAtual, Tarefa tarefaAcima) {
        String sql = "UPDATE tarefa SET ordem_de_apresentacao = ? WHERE id = ?";

        jdbcTemplate.update(sql, tarefaAcima.getOrderApresentacao(), tarefaAtual.getId());
        jdbcTemplate.update(sql, tarefaAtual.getOrderApresentacao(), tarefaAcima.getId());
    }

    @Override
    public void updateTarefa(Tarefa update) {
        if (update.getCusto() != null) {
            String sql = "UPDATE tarefa SET custo = ? WHERE id = ?";
            jdbcTemplate.update(sql, update.getCusto(), update.getId());
        }
        if (update.getDataLimite() != null) {
            String sql = "UPDATE tarefa SET data_limite = ? WHERE id = ?";
            jdbcTemplate.update(sql, update.getDataLimite(), update.getId());
        }

        if (update.getNome() != null && !update.getNome().isEmpty()) {
            String sql = "UPDATE tarefa SET nome = ? WHERE id = ?";
            jdbcTemplate.update(sql, update.getNome(), update.getId());
        }

    }

    @Override
    public Integer countTarefas() {
        var sql = """
                SELECT COUNT(*) FROM tarefa
                """;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
