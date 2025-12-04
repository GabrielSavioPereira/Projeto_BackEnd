api_url = "http://localhost:8080/transacoes"
const usuarioID = localStorage.getItem("usuarioID").toString();
const form = document.getElementById("formCriar")
const msgOk = document.getElementById("msgOk")
const msgErr = document.getElementById("msgErr")

const categoriasSel = document.getElementById("categorias")


document.getElementById("btnCancel").addEventListener("click", () => window.location.href = 'transacoes.html')

form.addEventListener("submit", async(e) => {
    e.preventDefault()
    
    msgOk.style.display = 'none'
    msgErr.style.display = 'none'

    const dto = {
        descricao: document.getElementById("descricao").value.trim(),
        valor: Number(document.getElementById("valor").value),
        tipo: document.getElementById("tipo").value,
        dataMovimentacao: document.getElementById("dataMovimentacao").value,
        contaId: document.getElementById("contaId").value,
        categoriaId: document.getElementById("categoriaId").value,
        usuarioId: usuarioID
    };

    if (!dto.descricao || !dto.valor || !dto.dataMovimentacao){
        return showError('Preencha todos os campos obrigatórios (descricao, valor, data)')
    }

    try {
        const res = await fetch(api_url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(dto)
        })

        if (!res.ok){
            const txt = await res.text()
            throw new Error(txt || res.statusText)
        }

        const created = await res.json();
        showSucess('Transação criada com sucesso!')


    } catch(err){
        console.error(err)
        showError(err.message || 'erro ao realizar a criação da transação')
    }
})

function showSucess(txt) {
    msgOk.style.display = 'block'
    msgOk.textContent = txt
}

function showError(txt){
    msgErr.style.display = 'block'
    msgErr.textContent = txt
}


document.addEventListener("DOMContentLoaded", () => {
    atualizaInputs();
})


async function atualizaInputs() {
    const url = "http://localhost:8080"
    const categorias = url + `/categorias/usuario/${usuarioID}`

    const res = await fetch(categorias)

    if (!res) throw new Error("Erro ao buscar as categorias")
    
    const pageJson = await res.json()
    categoriasSel.innerHTML = ''

    pageJson.forEach(tx => {
        categoriasSel.innerHTML += `
            <option value="${tx.id}">${tx.nome}</option>
        `
    });
    console.log(pageJson)

}