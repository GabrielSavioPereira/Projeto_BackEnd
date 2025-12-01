const api_url = "http://localhost:8080/transacoes"
const usuarioId = localStorage.getItem("usuarioID")

let page = 0
let size = Number(document.getElementById('pageSize').value || 10)

const tbodyTx = document.getElementById("tbodyTx")
const pageInfo = document.getElementById("pageInfo")
const prevBtn = document.getElementById("prevBtn")
const nextBtn = document.getElementById("nextBtn")
const errList = document.getElementById("errList")

const filtroTipo = document.getElementById("filtroTipo")
const filtroInicio = document.getElementById("filtroInicio")
const filtroFim = document.getElementById("filtroFim")
const btnFiltro = document.getElementById("btnFiltro")
const btnLimpar = document.getElementById("btnLimpar")

const fileInput = document.getElementById("fileInput")
const btnImport = document.getElementById("btnImport")
const msgImport = document.getElementById("msgImport")
const errImport = document.getElementById("errImport")
const pageSizeSel = document.getElementById("pageSize")


function showSucess(el, txt) {
    el.style.display = 'block'
    el.textContent = txt
    setTimeout(() => el.style.display='none', 4000);
}

function showError(el, txt) {
    el.style.display = 'block'
    el.textContent = txt
    setTimeout(() => el.style.display='none', 6000)
}

function builListUrl() {
    const params = new URLSearchParams()

    params.append('usuarioId', usuarioId)
    params.append('page', page)
    params.append('size', size)

    if (filtroTipo.value) params.append('tipo', filtroTipo.value)
    if (filtroInicio.value) params.append('inicio', filtroInicio.value)
    if (filtroFim.value) params.append('fim', filtroFim.value)

    return `${api_url}?${params.toString()}`
}

async function carregar() {
    errList.style.display = 'none'

    try{

        const url = builListUrl()
        const res = await fetch(url)

        if (!res) throw new Error('Erro ao buscar transações: ', res.statusText)
        const pageObj = await res.json()

        const content = pageObj.content || []
        tbodyTx.innerHTML = ''

        if (content.length === 0) {
            tbodyTx.innerHTML = '<tr><td colspan="6" class="muted"> Nenhum registro encontrado.</td></tr>'
        }else {
            content.forEach(tx => {
                tbodyTx.innerHTML += `
                    <tr>
                        <td>${tx.descricao ?? '-'}</td>
                        <td>${tx.valor ?? '-'}</td>
                        <td>${tx.dataMovimentacao ?? tx.data ?? '-'}</td>
                        <td>${tx.tipo ?? '-'}</td>
                        <td>${tx.contaId ?? '-'}</td>
                        <td>
                            <button class="small" onclick="deletar('${tx.id}')">Excluir</button>
                        </td>
                    </tr>
                `
            });

            const number = pageObj.number ?? page
            const totalPages = pageObj.totalPages ?? 1;
            pageInfo.textContent = `Página ${number + 1} de ${totalPages}`
            prevBtn.disabled = pageObj.first ?? (number == 0)
            nextBtn.disabled = pageObj.last ?? (number >= totalPages - 1)
        }

    } catch(err){
        console.error(err)
        showError(errList, err.message || 'Erro desconhecido')
    }
}

prevBtn.addEventListener('click', () => {
    if (page > 0) {
        page--;
        carregar();
    }
})

nextBtn.addEventListener('click', () => {
    page++
    carregar()
})

pageSizeSel.addEventListener("change", () => {
    size = Number(pageSizeSel.value)
    page = 0 
    carregar()
})

async function deletar(id){
    if(!confirm('Confirma exclusão')) return ;

    try{

        const res = await fetch(`${api_url}/${id}`, {
            method: 'DELETE'
        })

        if (!res.ok) throw new Error('Erro ao excluir: ' + res.statusText);

        await carregar()

    } catch(err) {
        alert('Erro ao excluir: ', err.message)
    }
}
window.deletar = deletar;

btnImport.addEventListener('click', async () => {
    console.log("cheguei na importação")
    errImport.style.display = 'none'
    msgImport.style.display = 'none'

    const file = fileInput.files[0]
    if (!file) return showError(errImport, 'Selecione um arquivo .xlsx')

    const fd = new FormData()
    fd.append('file', file)

    console.log(fd)

    try{

        const res = await fetch(`${api_url}/importar`,{
            method: 'POST',
            body: fd
        })
        const text = await res.text()

        if (!res.ok) {
            showError(errImport, `Erro: ${text}`)
            return
        }

        showSucess(msgImport, text)
        carregar()
        
    } catch(err) {
        console.error(err)
        showSucess(errImport, 'Erro ao enviar arquivo: ' + err.message) 
    }
})

carregar()




