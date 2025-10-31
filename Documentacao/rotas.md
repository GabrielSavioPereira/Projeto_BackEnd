### 👤 Usuários
Gerencia o cadastro dos usuários do sistema.

| Funcionalidade    | Método | Rota                | Descrição |
|-------------------|--------|---------------------|------------|
| Cadastrar usuário | POST   | `/registra-usuario` | Cria um novo usuário. |
| Login usuários    | POST   | `/login`            | Da acesso para o usuário em outras funcionalidades do programa. |
| Atualizar usuário | PUT    | `/usuarios/{id/cpf}`    | Atualiza as informações de um usuário. |

---

### 💰 Transações (Contas a Pagar/Receber)
Gerencia os registros financeiros de receitas e despesas.  
Cada transação afeta o saldo da conta vinculada.

| Funcionalidade | Método | Rota | Descrição |
|----------------|--------|------|------------|
| Cadastrar transação | POST | `/transacoes` | Registra uma nova transação e atualiza o saldo da conta. |
| Listar transações | GET | `/transacoes` | Retorna todas as transações do usuário. Suporta paginação e filtros (tipo, categoria, data). |
| Detalhar transação | GET | `/transacoes/{id}` | Exibe os detalhes de uma transação. |
| Atualizar transação | PUT | `/transacoes/{id}` | Modifica os dados de uma transação existente. |
| Excluir transação | DELETE | `/transacoes/{id}` | Remove uma transação e atualiza o saldo da conta. |

---

### 🏦 Contas
Representa as contas do usuário, como conta corrente, poupança ou carteira.

| Funcionalidade | Método | Rota | Descrição |
|----------------|--------|------|------------|
| Cadastrar conta | POST | `/contas` | Cria uma nova conta. |
| Listar contas | GET | `/contas` | Exibe todas as contas do usuário. |
| Consultar saldo total | GET | `/contas/saldo_total` | Calcula o saldo total somando todas as contas do usuário. |

---

### 🏷️ Categorias
Define classificações para as transações (ex: Alimentação, Transporte, Salário).

| Funcionalidade | Método | Rota | Descrição |
|----------------|--------|------|------------|
| Cadastrar categoria | POST | `/categorias` | Cria uma nova categoria. |
| Listar categorias | GET | `/categorias` | Exibe todas as categorias do usuário. |

---

### 📈 Investimentos
Registra ativos financeiros do usuário, como ações e fundos.

| Funcionalidade | Método | Rota | Descrição |
|----------------|--------|------|------------|
| Registrar investimento | POST | `/investimentos` | Adiciona um novo investimento. |
| Listar investimentos | GET | `/investimentos` | Lista todos os investimentos cadastrados. |

---
