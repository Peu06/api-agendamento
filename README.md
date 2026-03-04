# api-agendamento

# 🚀 sistema-agendamento-springboot

Este repositório contém uma **API REST desenvolvida com Spring Boot**, criada com o objetivo de **praticar desenvolvimento backend em Java** através da construção de um sistema real de **gestão de agendamentos** (barbearias, clínicas, salões, etc.), utilizando arquitetura em camadas e integração com banco de dados PostgreSQL.

## 🚧É UM PROJETO EM CONSTRUÇÃO, ALGUMAS FUNCIONALIDADES AINDA NÃO ESTÃO FUNCIONANDO.🚧
---

## 🛠️ Tecnologias utilizadas

- Java  
- Spring Boot  
- Spring Data JPA  
- MySQL  
- Maven  
- Lombok  
- REST API  

---

## 📌 Objetivo do Projeto

O sistema foi desenvolvido para simular um cenário real de mercado, permitindo:

- Cadastro de estabelecimentos  
- Vinculação de funcionários a um estabelecimento  
- Definição de serviços oferecidos por cada unidade  
- Configuração de horários de trabalho  
- Cadastro de clientes  
- Criação e gerenciamento de agendamentos  
- Controle de disponibilidade automática  
- Evitar conflitos de horários  

---

## 🏗️ Arquitetura

O projeto segue o padrão de **arquitetura em camadas**, separando responsabilidades:

- controller → Recebe as requisições HTTP
- service → Contém as regras de negócio
- repository → Comunicação com o banco de dados
- model → Entidades JPA
- dto → Objetos de entrada e saída


Essa organização facilita manutenção, testes e escalabilidade.

---

## 📊 Modelo de Domínio

As principais entidades do sistema são:

- **Estabelecimento** → Unidade do negócio  
- **Funcionario** → Profissional vinculado ao estabelecimento  
- **Servico** → Tipo de atendimento oferecido  
- **Cliente** → Usuário que realiza o agendamento  
- **Agenda** → Grade de horários do funcionário  
- **Agendamento** → Registro do atendimento marcado  

---

## 👨‍💻 Autor

Desenvolvido por João Pedro Caetano
Estudante de Análise e Desenvolvimento de Sistemas
Focado em Backend Java com Spring Boot
