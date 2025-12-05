# Projeto_BackEnd (CashInt)
---
# Integrantes do Grupo:

<ul>
    <li>
        <a href="https://github.com/ElyeserGabrian">Elyeser Gabrian Nunes</a>
    </li>
    <li>
        <a href="https://github.com/GabrielSavioPereira">Gabriel Savio Pereira</a>
    </li>
    <li>
        <a href="https://github.com/VitorBotome">Vitor Botome</a>
    </li>
</ul>

# 💡 Tema: 
Sistema de Controle Financeiro Pessoal

# 🎯 Objetivo

Criar uma API para que o usuário controle suas receitas, despesas e categorias financeiras — podendo gerar relatórios e visualizar seu saldo.

---
## Documentação Entrega 02 do Projeto

<a href="https://github.com/GabrielSavioPereira/Projeto_BackEnd/blob/main/Documentacao/documentacao_entrega2.MD"> Clique Neste link para acessar a Entrega02.</a>

---

## Documentação Entrega 03 do Projeto

<a href="https://github.com/GabrielSavioPereira/Projeto_BackEnd/blob/main/Documentacao/rotas.md"> Clique Neste link para acessar a Entrega03.</a>

---

## Técnologias Utilizadas

<img src="https://skillicons.dev/icons?i=git,html,css,java,mysql,postgres" />

---
## Limitações do projeto


---
## Descrição de cada uma das entidades

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
| saldo | Decimal(10,2) | Saldo da conta do usuário. |
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
## Descrição de cada uma das rotas, contendo exemplos de requisições e respostas;

### Transações

Criação de uma nova transação

POST /transacoes


```json
REQUISIÇÃO - Payload no body

{
  "descricao": "Compra no mercado",
  "valor": 125.50,
  "tipo": "DESPESA",
  "dataMovimentacao": "2025-02-01",
  "contaId": "id_da_conta",
  "categoriaId": "id_da_categoria",
  "usuarioId": "id_do_usuario"
}

RESPOSTA - 201 Created

{
  "id": "id_gerado_da_nova_transacao",
  "descricao": "Compra no mercado",
  "valor": 125.50,
  "data": "2025-02-01",
  "tipo": "DESPESA",
  "contaId": "id_da_conta",
  "categoriaId": "id_da_categoria",
  "usuarioId": "id_do_usuario"
}
```

Listar todas transações de um usuario

GET /transacoes/usuario/{usuarioId}

Exemplo: 
```json


REQUISIÇÃO 

GET /transacoes/usuario/123456?page=0&size=10

RESPOSTA - 200 OK

{
    "content": [
        {
            "id": "1",
            "descricao": "Compra mercado",
            "valor": 150.75,
            "data": "2025-01-10",
            "tipo": "DESPESA",
            "contaId": "123",
            "categoriaId": "123",
            "usuarioId": "123"
        },
        {
            "id": "2",
            "descricao": "Salário",
            "valor": 3200.00,
            "data": "2025-01-05",
            "tipo": "RECEITA",
            "contaId": "123",
            "categoriaId": "123",
            "usuarioId": "123"
        },
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 3,
    "first": true,
    "size": 10,
    "number": 0,
    "numberOfElements": 3,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "empty": false
}

```

Buscar uma trasação pelo ID

GET /transacoes/{id_da_transacao}

Exemplo:
```json
REQUISIÇÃO

GET /transacoes/1

RESPOSTA - 200 OK

{
  "id": "123",
  "descricao": "Compra no mercado",
  "valor": 125.50,
  "data": "2025-02-01",
  "tipo": "DESPESA",
  "contaId": "123",
  "categoriaId": "123",
  "usuarioId": "123"
}
```

Atualizar uma transação

PUT /transacoes/{id}

```json
REQUISIÇÃO

PUT /transacoes/1

Payload de exemplo

{
  "descricao": "Compra no mercado - atualizado",
  "valor": 150.00,
  "tipo": "DESPESA",
  "dataMovimentacao": "2025-02-01",
  "contaId": "123",
  "categoriaId": "123",
  "usuarioId": "123"
}

RESPOSTA - 200 OK

{
  "id": "1",
  "descricao": "Compra no mercado - atualizado",
  "valor": 150.00,
  "data": "2025-02-01",
  "tipo": "DESPESA",
  "contaId": "123",
  "categoriaId": "123",
  "usuarioId": "123"
}
```

Deletar uma transação

DELETE /transacoes/{id}

Exemplo:
```json
DELETE /transacoes/1

RESPOSTA - 204 No Content

Sem corpo na resposta
```

Importar transações de uma planilha XLSX

POST /transacoes/importar

Exemplo:
```json
POST /transacoes/importar
Content-Type: multipart/form-data
file: nome_do_arquivo.xlsx

RESPOSTA - 200 OK

Importação concluida com sucesso!
```

### Usuário

Criação de um novo usuario

POST /usuarios/registrar

```json
REQUISIÇÃO

PAYLOAD JSON

{
  "nome": "usuario teste",
  "email": "usuarioteste@email.com",
  "senha": "1234teste",
  "cpf": "12345678900",
  "dataNasc": "2000-05-12"
}

RESPOSTA - 200 OK

"id_do_usuario"
```
Realização do login do usuario - retorna as informações completas do usuario

POST /usuarios/login

```json
REQUISIÇÃO

PAYLOAD JSON

{
    "email": "usuarioteste@email.com",
    "senha": "1234teste"
}

RESPOSTA - 200 OK

{
    "id": "0229c8dd-511a-4331-9549-a86c0d751e8e",
    "nome": "usuario teste",
    "email": "usuarioteste@email.com",
    "senha": "1234teste",
    "cpf": "12345678900",
    "dataNasc": "2000-05-12"
}
```

Buscar o usuario por id

GET /usuarios/buscar/{id}

Exemplo
```json
REQUISIÇÃO

GET /usuarios/buscar/0229c8dd-511a-4331-9549-a86c0d751e8e

RESPOSTA - 200 OK

{
    "id": "0229c8dd-511a-4331-9549-a86c0d751e8e",
    "nome": "usuario teste",
    "email": "usuarioteste@email.com",
    "senha": "1234teste",
    "cpf": "12345678900",
    "dataNasc": "2000-05-12"
}
```

Atualizar usuario

PUT /usuarios/atualizar/{id}

Exemplo

REQUISIÇÃO

PAYLOAD JSON

{
    "nome": "usuario teste novo",
    "email": "usuarioteste@email.com",
    "senha": "1234teste",
    "cpf": "12345678900",
    "dataNasc": "2000-05-12"
}


RESPOSTA 200 - OK

{
    "id": "0229c8dd-511a-4331-9549-a86c0d751e8e",
    "nome": "usuario teste novo",
    "email": "usuarioteste@email.com",
    "senha": "1234teste",
    "cpf": "12345678900",
    "dataNasc": "2000-05-12"
}













---
## Exemplos de erros HTTP;
---
## Descrição de como executar o projeto localmente;

---
## Outros conteúdos relevantes implementados no projeto.
