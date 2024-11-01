let tarefaId = null;
window.addEventListener("load", function () {
    fetch("http://localhost:8080/v1/api/tarefa", {
        method: "GET",
        headers: {"Content-type": "application/json;charset=UTF-8"},
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
            <button onclick="switchModalEditar(${item.id})">Editar</button>
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

function adicionarTarefa(event) {
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
        headers: {"Content-type": "application/json;charset=UTF-8"},
    })
        .then((response) => {
            response.json();

        })
        .then((json) => {
            window.reload();
        });
}


function editarTarefa() {

    let nome = this.document.getElementById("nome_editado").value;
    let custo = this.document.getElementById("custo_editado").value;
    let data = this.document.getElementById("dataLimite_editado").value;

    let _data = {
        nome: nome,
        custo: custo,
        dataLimite: data,
    };
    fetch(`http://localhost:8080/v1/api/tarefa/${tarefaId}`, {
        method: "PUT",
        body: JSON.stringify(_data),
        headers: {"Content-type": "application/json;charset=UTF-8"},
    })
        .then((response) => {
            response.json();
        })
        .then((json) => {
            window.reload();
        });
}


function apagar(id) {
    fetch(`http://localhost:8080/v1/api/tarefa/${id}`, {
        method: "DELETE",
        headers: {"Content-type": "application/json;charset=UTF-8"},
    })
        .then((response) => {
            location.reload();
        })
        .then((json) => console.log(json));
}

function taskUp(id) {
    fetch(`http://localhost:8080/v1/api/tarefa/ordemUp/${id}`, {
        method: "PUT",
        headers: {"Content-type": "application/json;charset=UTF-8"},
    })
        .then((response) => {
            location.reload();
        })
        .then((json) => console.log(json));
}

function taskDown(id) {
    fetch(`http://localhost:8080/v1/api/tarefa/ordemDown/${id}`, {
        method: "PUT",
        headers: {"Content-type": "application/json;charset=UTF-8"},
    })
        .then((response) => {
            location.reload();
        })
        .then((json) => console.log(json));
}

function switchModal() {
    const modal = document.querySelector('.modal')

    const actualStyle = modal.style.display

    if (actualStyle === 'block') {
        modal.style.display = 'none'
    }
    if (actualStyle !== 'block') {
        modal.style.display = 'block'
    }

}

function switchModalEditar(id) {
    const modal = document.querySelector('.modal_editar')
    tarefaId = id;
    const actualStyle = modal.style.display

    if (actualStyle === 'block') {
        modal.style.display = 'none'
    }
    if (actualStyle !== 'block') {
        modal.style.display = 'block'
    }

}


window.onclick = function (event) {
    const modal = document.querySelector('.modal')
    if (event.target === modal) {
        switchModal()
    }
}