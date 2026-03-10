// ===== URLS =====
const API_BASE = "http://localhost:8081";
const API_CLIENTE = `${API_BASE}/cliente`;
const API_ESTABELECIMENTO = `${API_BASE}/estabelecimento`;
const API_LOGIN = `${API_CLIENTE}/login`;

// ===== FUNÇÕES DE API =====

// Criar estabelecimento
async function criarEstabelecimento(estabelecimento) {
    try {
        const response = await fetch(API_ESTABELECIMENTO, {
            method: 'POST',
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(estabelecimento)
        });
        if (!response.ok) throw new Error(`Erro ao criar estabelecimento: ${response.status}`);
        const novoEstabelecimento = await response.json();
        console.log("Estabelecimento adicionado:", novoEstabelecimento);
    } catch (error) {
        console.error(error);
    }
}

// Criar cliente
async function criarCliente(cliente) {
    try {
        const response = await fetch(API_CLIENTE, {
            method: 'POST',
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(cliente)
        });

        if (!response.ok) {
            const erro = await response.json();
            throw new Error(erro.message || "Erro ao criar cliente");
        }

        const novoCliente = await response.json();
        console.log("Cliente adicionado:", novoCliente);
        return { sucesso: true, cliente: novoCliente };
    } catch (error) {
        console.error(error);
        return { sucesso: false, mensagem: error.message };
    }
}

// Carregar estabelecimento
async function carregarEstabelecimento(id) {
    try {
        const response = await fetch(`${API_ESTABELECIMENTO}/${id}`);
        if (!response.ok) throw new Error("Estabelecimento não encontrado");
        const est = await response.json();

        document.getElementById("nomeEstabelecimento")?.textContent = est.nome;
        document.getElementById("produtoEstabelecimento")?.textContent = est.produto;
        document.getElementById("telefoneEstabelecimento")?.textContent = est.telefone || "Não informado";
        document.getElementById("emailEstabelecimento")?.textContent = est.email || "Não informado";
        document.getElementById("enderecoEstabelecimento")?.textContent = est.endereco;
        document.getElementById("proprietarioEstabelecimento")?.textContent = est.proprietario?.nome || "Não informado";
    } catch (error) {
        console.error(error);
        document.body.innerHTML = "<p>Erro ao carregar estabelecimento.</p>";
    }
}

// Listar estabelecimentos
async function listarEstabelecimentos() {
    const container = document.getElementById("listaEstabelecimentos");
    if (!container) return;

    try {
        const response = await fetch(API_ESTABELECIMENTO);
        if (!response.ok) throw new Error("Erro ao buscar estabelecimentos");

        const estabelecimentos = await response.json();
        container.innerHTML = "";
        estabelecimentos.forEach(est => {
            container.innerHTML += criarCardEstabelecimento(est);
        });
    } catch (error) {
        console.error("Erro ao listar estabelecimentos:", error);
        container.innerHTML = `<div class="alert alert-danger">Não foi possível carregar os estabelecimentos.</div>`;
    }
}

// Criar card de estabelecimento
function criarCardEstabelecimento(est) {
    return `
        <div class="col-md-4 mb-4">
            <div class="card h-100 shadow card-estabelecimento">
                <div class="card-body">
                    <h5 class="card-title">${est.nome}</h5>
                    <p class="card-text"><strong>Serviço:</strong> ${est.produto}</p>
                    <p class="card-text">📍 ${est.endereco}</p>
                    <p class="card-text">📞 ${est.telefone || "Não informado"}</p>
                    <a href="estabelecimento.html?id=${est.id}" class="btn btn-primary w-100">Ver detalhes</a>
                </div>
            </div>
        </div>
    `;
}

// ===== LOGIN =====
async function loginUsuario(email, senha) {
    try {
        const response = await fetch(API_LOGIN, {
            method: 'POST',
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, senha })
        });

        if (!response.ok) {
            const erro = await response.json();
            throw new Error(erro.message || "Email ou senha inválidos");
        }

        const dados = await response.json();
        console.log("Login bem-sucedido:", dados);
        return { sucesso: true, dados };
    } catch (error) {
        console.error(error);
        return { sucesso: false, mensagem: error.message };
    }
}

// ===== EVENT LISTENERS =====

// Formulário de criação de cliente
const formCliente = document.getElementById("form-criar-conta");
const feedbackConta = document.getElementById("feedback-conta");

if (formCliente) {
    formCliente.addEventListener("submit", async (event) => {
        event.preventDefault();

        const cliente = {
            nome: document.getElementById("nome").value,
            email: document.getElementById("email").value,
            senha: document.getElementById("senha").value,
            dtNascimento: document.getElementById("dtNascimento").value || null,
            telefone: document.getElementById("telefone").value,
            cpf: document.getElementById("cpf").value
        };

        const resultado = await criarCliente(cliente);

        if (resultado.sucesso) {
            window.location.href = "login.html";
        } else {
            feedbackConta.innerHTML = `
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${resultado.mensagem || "Erro: Email ou CPF já estão em uso."}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            `;
        }
    });
}

// Formulário de login
const formLogin = document.getElementById("form-login");
const feedbackLogin = document.getElementById("feedback-login");

if (formLogin) {
    formLogin.addEventListener("submit", async (event) => {
        event.preventDefault();

        const email = document.getElementById("email").value.trim();
        const senha = document.getElementById("senha").value.trim();

        if (!email || !senha) {
            feedbackLogin.innerHTML = `
                <div class="alert alert-danger alert-dismissible fade show mt-2" role="alert">
                    Preencha todos os campos!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            `;
            return;
        }

        const resultado = await loginUsuario(email, senha);

        if (resultado.sucesso) {
            window.location.href = "index.html";
        } else {
            feedbackLogin.innerHTML = `
                <div class="alert alert-danger alert-dismissible fade show mt-2" role="alert">
                    ${resultado.mensagem || "Email ou senha incorretos."}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            `;
        }
    });
}

// Carregar ou listar estabelecimentos se necessário
const urlParams = new URLSearchParams(window.location.search);
const idEstabelecimento = urlParams.get("id");
if (idEstabelecimento) {
    carregarEstabelecimento(idEstabelecimento);
} else {
    const nomeEl = document.getElementById("nomeEstabelecimento");
    if (nomeEl) nomeEl.textContent = "Nenhum estabelecimento selecionado.";
    listarEstabelecimentos();
}