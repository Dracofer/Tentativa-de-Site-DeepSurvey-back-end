🛠 DeepSurvey API — Backend (Spring Boot + MySQL + JWT)

Este é o backend do sistema DeepSurvey Suplementos, uma plataforma completa de e-commerce com delivery por WhatsApp.
A API fornece:

Cadastro e autenticação de usuários (JWT)

Gerenciamento de produtos, categorias, fretes e configurações da loja

Carrinho baseado em sessionId

Finalização de pedido via WhatsApp

Painel administrativo protegido por ROLE_ADMIN

🚀 Tecnologias Utilizadas

Java 17+

Spring Boot

Spring Security + JWT

Spring Data JPA / Hibernate

MySQL

Maven

📦 Funcionalidades da API
🔐 Autenticação

Login JWT

Validação automática do token

Controle de roles:

ROLE_USER

ROLE_ADMIN

🛍 Produtos

CRUD completo

Preço promocional

Suporte a múltiplas imagens

🗂 Categorias

CRUD

Relacionamento 1:N com produtos

🚚 Regiões de Entrega

CRUD

Controle de valor de frete

Ativar/desativar regiões

🏬 Configurações da Loja

Logo

Tema

Cores

Pedido mínimo

WhatsApp

Endereço

Status da loja (aberto/fechado)

🛒 Carrinho

SessionId persistido pelo frontend

Adicionar, atualizar, remover itens

📦 Pedido (Checkout)

Cálculo de subtotal + promoções

Cálculo automático de frete

Geração de mensagem formatada

Retorno para integração com WhatsApp

⚙️ Como Rodar o Projeto
1️⃣ Pré-requisitos

Java 17

Maven

MySQL

2️⃣ Criar o Banco de Dados
CREATE DATABASE lojazap CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

3️⃣ Configurar o application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/lojazap?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8083

# JWT
app.jwt.secret=VerySecretJwtKeyChangeMe
app.jwt.expirationMs=86400000


⚠️ Importante: altere o JWT secret em produção.

4️⃣ Rodar o Backend
mvn spring-boot:run


A API iniciará em:

http://localhost:8083

🧪 Endpoints Principais
🔓 Públicos
GET /products
GET /products/{id}
GET /products/search?q=
GET /categories
GET /store-config
POST /cart/add
POST /orders/checkout

🔐 Requer token (usuário logado)
GET /cart/{sessionId}
POST /cart/update
POST /cart/remove

🔐 Requer ROLE_ADMIN
POST/PUT/DELETE /products
POST/PUT/DELETE /categories
POST/PUT/DELETE /delivery-regions
PUT /store-config/{id}

🧩 Estrutura de Pastas
src/main/java/com/example/deepsurvey/
│
├── controller/        (controladores REST)
├── model/             (entidades JPA)
├── repository/        (interfaces JPA)
├── service/           (regras de negócio)
├── security/          (JWT, filtros, config de segurança)
└── DeepsurveyApplication.java

🔐 Segurança

Tokens gerados via HS512

Roles armazenadas no payload JWT

Apenas admin acessa rotas administrativas

Tokens enviados no header:

Authorization: Bearer {token}

Considerações finais: esse projeto teve continuidade porem por questão de segurança do site vou postar de forma aberta somente até aqui.

📄 Licença

MIT License.
