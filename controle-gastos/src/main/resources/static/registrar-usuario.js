document.getElementById("bt-registrar").addEventListener("click", async () => {
    const nome = document.getElementById("nome").value
    const email = document.getElementById("email").value
    const cpf = document.getElementById("cpf").value
    const dataNasc = document.getElementById("dataNasc").value
    const senha = document.getElementById("senha").value
  
    const response =  await fetch("http://localhost:8080/usuarios/registrar", {
        method: "POST", 
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({nome, email, senha, cpf, dataNasc})
    })

    if (response.ok){
        data = response.json()
        console.log("Usuario criado!")

        console.log(data)
    }
})