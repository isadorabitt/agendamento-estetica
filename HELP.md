# Clínica Estética - Agendamento de Serviços

Este é um sistema de agendamento para clínicas estéticas desenvolvido em Java com Spring Boot. O projeto permite a criação de usuários, autenticação JWT e gerenciamento de agendamentos.

## 📌 Tecnologias Utilizadas

- **Java** (JDK 17 ou superior)
- **Spring Boot** (Framework para aplicações Java)
- **Spring Security** (Gerenciamento de autenticação e autorização)
- **JWT (JSON Web Token)** (Para autenticação segura)
- **Maven** (Gerenciamento de dependências)
- **Banco de Dados** (MySQL ou PostgreSQL)

## 🚀 Configuração do Ambiente

### 1️⃣ Instalar Dependências

Caso utilize Maven, as principais dependências já estão no `pom.xml`. Algumas delas:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.11.2</version>
</dependency>
```

### 2️⃣ Configurar o Banco de Dados

Crie o banco de dados no MySQL:

```sql
CREATE DATABASE clinica_estetica;
USE clinica_estetica;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);
```

No arquivo `application.properties` ou `application.yml`, configure as credenciais do banco de dados:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinica_estetica
spring.datasource.username=root
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
```

### 3️⃣ Executar o Sistema

Para rodar a aplicação localmente, utilize:

```sh
mvn spring-boot:run
```

Se quiser rodar com Docker:

```sh
docker-compose up --build
```

## 📌 Funcionalidades

- 📌 Criar e autenticar usuários (JWT)
- 📌 Gerenciar agendamentos
- 📌 Listar serviços disponíveis
- 📌 Segurança com Spring Security

## 🛠 Possíveis Erros e Soluções

### 🚨 `org.springframework.beans.factory.UnsatisfiedDependencyException`
**Solução:** Verifique se todas as dependências estão corretamente configuradas e se o banco de dados está rodando.

### 🚨 `java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver`
**Solução:** Certifique-se de que o driver do MySQL está incluído nas dependências do Maven.

## 📜 Licença

Este projeto é de uso livre para fins educacionais e pessoais.

---
Feito com ❤️ por Isadora


