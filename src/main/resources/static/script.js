window.addEventListener("load", function () {
    fetch("http://localhost:8080/v1/api/tarefa", {
        method: "GET",
        headers: { "Content-type": "application/json;charset=UTF-8" },
    })
        .then((response) => response.json())
        .then((data) => {
            const tableBody = document.querySelector("#result tbody");

            if (data && data.length > 0) {
                let rowsHtml = data
                    .map(
                        (item) => `
          <tr ${item.custoAlto ? 'style="background: yellow"' : ""}>
 
            <td>${item.nome}</td>
            <td>${item.custo}</td>
            <td>${item.dataLimite}</td>
            <td> 
            <button onclick="apagar(${item.id})">Apagar</button>
            </td>
            <td> 
            <button onclick="editar(${item.id})">Editar</button>
            </td>
            <td> 
            <button onclick="taskUp(${item.id})">Subir</button>
            </td>
            <td> 
            <button onclick="taskDown(${item.id})">Descer</button>
            </td>
          </tr>
        `
                    )
                    .join("");

                tableBody.innerHTML += rowsHtml;
            } else {
                tableBody.innerHTML = `
          <tr>
            <td colspan="3">Nenhum dado encontrado.</td>
          </tr>
        `;
            }
        })
        .catch((err) => {
            console.error("Erro ao carregar dados:", err);
            document.querySelector("#result tbody").innerHTML = `
        <tr>
          <td colspan="3">Erro ao carregar dados.</td>
        </tr>
      `;
        });
});

window.addEventListener("submit", function adicionarTarefa(event) {
    let nome = this.document.getElementById("nome_adicionado").value;
    let custo = this.document.getElementById("custo_adicionado").value;
    let data = this.document.getElementById("dataLimite").value;

    let _data = {
        nome: nome,
        custo: custo,
        dataLimite: data,
    };
    fetch("http://localhost:8080/v1/api/tarefa", {
        method: "POST",
        body: JSON.stringify(_data),
        headers: { "Content-type": "application/json;charset=UTF-8" },
    })
        .then((response) => response.json())
        .then((json) => console.log(json));
});

function apagar(id) {
    fetch(`http://localhost:8080/v1/api/tarefa/${id}`, {
        method: "DELETE",
        headers: { "Content-type": "application/json;charset=UTF-8" },
    })
        .then((response) => {
            location.reload();
        })
        .then((json) => console.log(json));
}

function taskUp(id) {
    fetch(`http://localhost:8080/v1/api/tarefa/ordemUp/${id}`, {
        method: "PUT",
        headers: { "Content-type": "application/json;charset=UTF-8" },
    })
        .then((response) => {
            location.reload();
        })
        .then((json) => console.log(json));
}
function taskDown(id) {
    fetch(`http://localhost:8080/v1/api/tarefa/ordemDown/${id}`, {
        method: "PUT",
        headers: { "Content-type": "application/json;charset=UTF-8" },
    })
        .then((response) => {
            location.reload();
        })
        .then((json) => console.log(json));
}


//http://localhost:8080/v1/api/tarefa post
//http://localhost:8080/v1/api/tarefa/1 put
//http://localhost:8080/v1/api/tarefa/1 delete
// http://localhost:8080/v1/api/tarefa/ordemUp/4 put
