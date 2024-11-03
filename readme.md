Este é um projeto básico usando Spring Boot. Este projeto serve como ponto de partida para criar uma aplicação web em Java com Spring Boot, que pode ser acessada no navegador em `localhost:8080`.

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior
- [Maven](https://maven.apache.org/download.cgi) (opcional, pois o Spring Boot possui um wrapper que pode ser usado)
- Um editor de código (como IntelliJ IDEA, Eclipse, ou Visual Studio Code)

## Como iniciar o projeto

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/pedrojobs13/todoList
   cd todoList
   ```

2. **Compilar o projeto:**

   Você pode compilar o projeto com Maven. Execute o seguinte comando na raiz do projeto:

   ```bash
   ./mvn clean install
   ```

3. **Iniciar a aplicação:**

   Para iniciar a aplicação Spring Boot, execute o comando abaixo:

   ```bash
   ./mvn spring-boot:run
   ```

   Alternativamente, você pode compilar o JAR e executá-lo diretamente:

   ```bash
   ./mvn clean package
   java -jar target/tarefa-0.0.1-SNAPSHOT.jar
   ```

4. **Acessar o site:**

   Após a aplicação iniciar com sucesso, você pode acessar o site no navegador:

   ```
   http://localhost:8080
   ```

## Estrutura do Projeto

- `src/main/java`: Contém o código-fonte Java.
- `src/main/resources`: Contém os arquivos de configuração, incluindo o `application.yml.
- `target`: Contém os arquivos compilados após a execução dos comandos de build.

## Configuração Adicional

- **Porta:** Por padrão, o Spring Boot inicia na porta `8080`. Você pode alterar essa configuração no arquivo `src/main/resources/application.properties`:

  ```properties
  server.port=8081
  ```

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Maven**

## Contribuição

Sinta-se à vontade para contribuir para o projeto. Faça um fork e envie pull requests!

---