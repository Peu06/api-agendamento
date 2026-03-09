// URLs da API
const API_BASE = "http://localhost:8081";

const API_CLIENTE       = `${API_BASE}/cliente`;
const API_FUNCIONARIO   = `${API_BASE}/funcionario`;
const API_ESTABELECIMENTO = `${API_BASE}/estabelecimento`;
const API_PROPRIETARIO  = `${API_BASE}/proprietario`;
const API_AGENDA        = `${API_BASE}/agenda`;
const API_AGENDAMENTO   = `${API_BASE}/agendamento`;

//Função adicionar estabelecimento
async function criarEstabelecimento(estabelecimento) {
    try {
        const response = await fetch(API_ESTABELECIMENTO, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(estabelecimento)
        });
        if (!response.ok) {
            throw new Error(`Erro ao criar estabelecimento: ${response.status}`)
        }
        const novoEstabelecimento = await response.json();
        console.log("Estabelecimento adicionado: ", novoEstabelecimento);
    } catch (error) {
        console.error(error);
    }
}

//Função buscar estabelecimento
async function buscarEstabelecimento() {
    try{
        const response = await fetch(API_ESTABELECIMENTO)
        if(!response.ok){
            throw new Error(`Erro ao buscar estabelecimento: ${response.status}`);
        }
        const estabelecimento = await response.json();
        console.log(estabelecimento);
    } catch (error) {
        console.error(error);
    }   
}

document.getElementById("formEstabelecimento")
.addEventListener("submit", async function (event) {

    event.preventDefault();
    
    const estabelecimento = {
        nome: document.getElementById("nome").value,
        produto: document.getElementById("produto").value,
        telefone: document.getElementById("telefone").value,
        email: document.getElementById("email").value,
        endereco: 
            document.getElementById("rua").value + ", " +
            document.getElementById("numero").value + ", " +
            document.getElementById("bairro").value + ", " +
            document.getElementById("cidade").value + "-" +
            document.getElementById("estado").value ,
        proprietario: {
            id: document.getElementById("proprietario").value
        }
    }
     await criarEstabelecimento(estabelecimento);
})