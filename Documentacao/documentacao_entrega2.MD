# 📘 Documentação da API – Sistema de Controle de Gastos Pessoal

## 🧩 Descrição Geral
A API será desenvolvida com o objetivo de gerenciar contas, categorias, transações e investimentos de um usuário, funcionando como uma planilha digital de controle financeiro.  
O sistema permite cadastrar receitas e despesas, calcular saldos e registrar investimentos, oferecendo uma visão geral das finanças pessoais.

Segue abaixo um escopo inicial da nossa ideia:
---

## ⚙️ Funcionalidades Principais

### 👤 Usuários
Gerencia o cadastro dos usuários do sistema.

| Funcionalidade    | Método | Rota                | Descrição |
|-------------------|--------|---------------------|------------|
| Cadastrar usuário | POST   | `/registra-usuario` | Cria um novo usuário. |
| Login usuários    | POST   | `/login`            | Da acesso para o usuário em outras funcionalidades do programa. |
| Atualizar usuário | PUT    | `/usuarios/{id}`    | Atualiza as informações de um usuário. |

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

## 🧠 Entidades e Atributos

### 🧍 Usuário
| Atributo  | Tipo | Descrição                            |
|-----------|------|--------------------------------------|
| id        | UUID/INT | Identificador único (chave primária). |
| nome      | String | Nome completo do usuário.            |
| email     | String | Email único usado para login.        |
| senha     | String | Senha armazenada de forma segura.    |
| cpf       | String | CPF do usuario.                      |
| data_nasc | String | Data de Nascimento do usuario.       |

---

### 🏦 Conta
| Atributo | Tipo | Descrição |
|-----------|------|------------|
| id | UUID/INT | Identificador único. |
| nome | String | Nome da conta (ex: Conta Corrente). |
| saldo_inicial | Decimal(10,2) | Saldo inicial no momento da criação. |
| saldo_atual | Decimal(10,2) | Saldo atualizado conforme as transações. |
| usuario_id | UUID/INT | Chave estrangeira para Usuário. |

---

### 🏷️ Categoria
| Atributo | Tipo | Descrição |
|-----------|------|------------|
| id | UUID/INT | Identificador único. |
| nome | String | Nome da categoria (ex: Alimentação, Salário). |
| tipo | Enum | Tipo: 'RECEITA' ou 'DESPESA'. |
| usuario_id | UUID/INT | Chave estrangeira para Usuário. |

---

### 💰 Transação
| Atributo | Tipo | Descrição |
|-----------|------|------------|
| id | UUID/INT | Identificador único. |
| descricao | String | Detalhes da transação. |
| valor | Decimal(10,2) | Valor da transação. |
| data | Date | Data da movimentação. |
| tipo | Enum | Tipo: 'RECEITA' ou 'DESPESA'. |
| conta_id | UUID/INT | Chave estrangeira para Conta. |
| categoria_id | UUID/INT | Chave estrangeira para Categoria. |
| usuario_id | UUID/INT | Chave estrangeira para Usuário. |

---

### 📈 Investimento
| Atributo | Tipo | Descrição |
|-----------|------|------------|
| id | UUID/INT | Identificador único. |
| nome | String | Nome do ativo (ex: PETR4). |
| tipo | Enum | Tipo do investimento (ex: Ação, Fundo). |
| valor_aplicado | Decimal(10,2) | Valor investido inicialmente. |
| rend_invest | Decimal(10,2) | Total de quanto irá render ao mês o valor investido. |
| data_aplicacao | Date | Data em que o investimento foi feito. |
| usuario_id | UUID/INT | Chave estrangeira para Usuário. |

---

📅 **Autores:** Elyeser Gabrian, Gabriel Sávio Pereira e Vítor Botomé <br>
🎓 **Curso:** Engenharia da Computação – Unisatc  
📘 **Matéria:** BackEnd
