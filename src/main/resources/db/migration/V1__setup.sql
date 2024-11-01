CREATE TABLE IF NOT EXISTS tarefa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    custo DECIMAL(10, 2) NOT NULL,
    data_limite TIMESTAMP NOT NULL,
    ordem_de_apresentacao INTEGER NOT NULL CHECK (ordem_de_apresentacao > 0)
);