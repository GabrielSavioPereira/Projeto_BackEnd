const usuarioID = localStorage.getItem("usuarioID");

document.addEventListener("DOMContentLoaded", () => {
    carregaDados();

    document.getElementById("bt-atualizar").addEventListener("click", async () => { 
        const nome = document.getElementById("nome").value;
        const email = document.getElementById("email").value;
        const cpf  = document.getElementById("cpf").value;
        const dataNasc = document.getElementById("dataNasc").value;
        const senha = document.getElementById("senha").value;

        const response = await fetch("http://localhost:8080/usuarios/atualizar/" + usuarioID.toString(), {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({nome, email, senha, cpf, dataNasc})
        });

        if (response.ok){
            console.log("Informações atualizadas");
        }

    })
})

async function carregaDados(){

    const response = await fetch("http://localhost:8080/usuarios/buscar/" + usuarioID.toString(), {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    });

    if (response.ok) {
        const data = await response.json();
        
        console.log(data)

        document.getElementById("nome").value = data.nome;
        document.getElementById("email").value = data.email;
        document.getElementById("cpf").value = data.cpf;
        document.getElementById("dataNasc").value = data.dataNasc;
        document.getElementById("senha").value = data.senha;

        console.log("Buscou as informações")
    }
}