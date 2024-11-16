let tarefaId = null;
let tarefaIdDelete = null;
let host = "https://todolist-lali.onrender.com/v1/api/tarefa";

window.addEventListener("load", function () {
    fetch(host, {
        method: "GET", headers: {"Content-type": "application/json;charset=UTF-8"},
    })
        .then((response) => response.json())
        .then((data) => {
            const tableBody = document.querySelector("#result tbody");

            if (data && data.length > 0) {
                let rowsHtml = data
                    .map((item) => `
          <tr ${item.custoAlto ? 'style="background: yellow"' : ""} class="row_column">
            <td class="name_table">${item.nome}</td>
            <td>${item.custo}</td>
            <td>${item.dataLimite}</td>
            <td> 
            <button onclick="switchModalApagar(${item.id})"><i class="fa fa-trash" aria-hidden="true"></i></button>
            </td>
            <td> 
            <button onclick="switchModalEditar(${item.id})"><i class="fa fa-pencil" aria-hidden="true"></i></button>
            </td>
            <td> 
            <button onclick="taskUp(${item.id})"><i class="fa fa-arrow-up" aria-hidden="true"></i></button>
            </td>
            <td> 
            <button onclick="taskDown(${item.id})"><i class="fa fa-arrow-down" aria-hidden="true"></i>
</button>
            </td>
          </tr>
        `)
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

function adicionarTarefa() {
    let nome = this.document.getElementById("nome_adicionado").value;
    let custo = this.document.getElementById("custo_adicionado").value;
    let data = this.document.getElementById("dataLimite").value;
    let _data = {
        nome: nome, custo: custo, dataLimite: data,
    };
    fetch(host, {
        method: "POST", body: JSON.stringify(_data), headers: {"Content-type": "application/json;charset=UTF-8"},
    }).then((response) => {
        if (response.status === 400) {
            return response.text().then((errorMessage) => {
                throw new Error(errorMessage);
            });
        }
    }).then((json) => {
        createToast("success", "Tarefa adicionada com sucesso!");
        location.reload();
    })
        .catch((error) => {
            createToast("error", error.message);
        });
}


function editarTarefa() {

    let nome = this.document.getElementById("nome_editado").value;
    let custo = this.document.getElementById("custo_editado").value;
    let data = this.document.getElementById("dataLimite_editado").value;

    let _data = {
        nome: nome, custo: custo, dataLimite: data,
    };
    fetch(`${host}/${tarefaId}`, {
        method: "PUT", body: JSON.stringify(_data), headers: {"Content-type": "application/json;charset=UTF-8"},
    })
        .then((response) => {
            response.json();
        })
        .then((json) => {
            window.reload();
        });
}


function apagar() {

    fetch(`${host}/${tarefaIdDelete}`, {
        method: "DELETE", headers: {"Content-type": "application/json;charset=UTF-8"},
    })
        .then((response) => {
            location.reload();
        })
        .then((json) => console.log(json));
}

function taskUp(id) {
    fetch(`${host}/ordemUp/${id}`, {
        method: "PUT", headers: {"Content-type": "application/json;charset=UTF-8"},
    })
        .then((response) => {
            location.reload();
        })
        .then((json) => console.log(json));
}

function taskDown(id) {
    fetch(`${host}/ordemDown/${id}`, {
        method: "PUT", headers: {"Content-type": "application/json;charset=UTF-8"},
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
    getById();
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


function switchModalApagar(id) {
    const modal = document.querySelector('.modal_delete')
    tarefaIdDelete = id;
    const actualStyle = modal.style.display

    if (actualStyle === 'block') {
        modal.style.display = 'none'
    }
    if (actualStyle !== 'block') {
        modal.style.display = 'block'
    }

}


const notifications = document.querySelector(".notifications"), buttons = document.querySelectorAll(".buttons .btn")

const toastDetails = {
    timer: 5000, success: {
        icon: "fa-circle-check", text: "Hello World: This is a success toast.",
    }, error: {
        icon: "fa-circle-xmark", text: "Hello World: This is an error toast.",
    }, warning: {
        icon: "fa-triangle-exclamation", text: "Hello World: This is a warning toast.",
    }, info: {
        icon: "fa-circle-info", text: "Hello World: This is an information toast.",
    }, random: {
        icon: "fa-solid fa-star", text: "Hello World: This is a random toast.",
    },
}

const removeToast = (toast) => {
    toast.classList.add("hide")
    if (toast.timeoutId) clearTimeout(toast.timeoutId)
    setTimeout(() => toast.remove(), 500)
}

const createToast = (id, text) => {
    const {icon} = toastDetails[id]
    const toast = document.createElement("li")
    toast.className = `toast ${id}`
    toast.innerHTML = `<div class="column">
                         <i class="fa-solid ${icon}"></i>
                         <span>${text}</span>
                      </div>
                      <i class="fa-solid fa-xmark" onclick="removeToast(this.parentElement)"></i>`
    notifications.appendChild(toast)
    toast.timeoutId = setTimeout(() => removeToast(toast), toastDetails.timer)
}

const getById = () => {
    fetch(`${host}/${tarefaId}`, {
        method: "GET", headers: {"Content-type": "application/json;charset=UTF-8"},
    }).then((response) => {
        return response.json();
    }).then((data) => {
        const modal = document.querySelector(".modal_editar .content");
        let modalInside =
            `

        <form onsubmit="editarTarefa()">
         <div class="close_button">
            <button onclick="switchModalEditar()">&times;</button>
        </div>
        <h2>Editar Tarefa</h2>
            <label for="nome_editado">Nome:</label>
            <input name="nome_editado" id="nome_editado" type="text"  value="${data.nome}"/>
            <br/><br/>
            <label for="custo_editado">Custo:</label>
            <input name="custo_editado" id="custo_editado" type="number" value="${data.custo}"/>
            <br/><br/>
            <label for="dataLimite_editado">Data Limite:</label>
            <input name="dataLimite_editado" id="dataLimite_editado" type="date" value="${data.dataLimite}"/>
            <br/><br/>
            <button type="submit">Enviar</button>
        </form>
           `;

        modal.innerHTML = modalInside;
        console.log(modalInside);
    });
}
