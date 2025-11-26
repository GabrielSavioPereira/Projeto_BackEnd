document.getElementById("bt-entrar").addEventListener("click", async () => {
    const email = document.getElementById("input-email").value;
    const senha = document.getElementById("input-pwd").value


       fetch("http://localhost:8080/usuarios/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, senha})
        })
        .then(async response => {
            const body = await response.text(); // <- aqui pega o body bruto
            console.log("STATUS:", response.status);
            console.log("BODY:", body);
        })
        .catch(err => console.error(err));
})