package com.fattocs.tarefa.integration.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class TarefaRowMapper implements RowMapper<Tarefa> {


    @Override
    public Tarefa mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Tarefa(rs.getLong("id"), rs.getString("nome"), rs.getDouble("custo"),
                rs.getDate("data_limite").toLocalDate(), rs.getInt("ordem_de_apresentacao"));
    }
}
