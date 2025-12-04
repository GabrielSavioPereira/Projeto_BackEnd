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
