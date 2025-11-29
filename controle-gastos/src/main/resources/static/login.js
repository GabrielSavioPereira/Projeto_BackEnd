document.getElementById("bt-entrar").addEventListener("click", async () => {
    const email = document.getElementById("input-email").value;
    const senha = document.getElementById("input-pwd").value


       const response  = await fetch("http://localhost:8080/usuarios/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, senha})
        });

        if (response.ok) {
            const data = await response.json();

            console.log(data)

            localStorage.setItem("usuarioID", data.id)

            window.location.href = "/home.html"
        } else {
            console.log(response.json())
        }
})