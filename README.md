
# Projeto exemplo - Loja (inspirado no Lojazap)

## O que está incluído
- Spring Boot backend (Java 11, Maven)
- MySQL (configure conforme suas credenciais)
- JWT authentication (endpoints /auth/register e /auth/login)
- Produtos (CRUD) em /products
- Carrinho em /cart (add, list, clear)
- Checkout em /orders/checkout

## Configuração rápida
1. Crie um banco MySQL chamado `shopdb` (ou altere `application.properties`).
2. No arquivo `src/main/resources/application.properties` está configurado:
   ```
   spring.datasource.url=jdbc:mysql://localhost:8081/shopdb
   spring.datasource.username=root
   spring.datasource.password=root
   server.port=8081
   ```
   > **Obs:** você informou que seu MySQL está na porta 8081; se seu MySQL roda na porta padrão 3306, altere a URL para `jdbc:mysql://localhost:3306/shopdb`.

3. Abra o projeto no Spring Tools for Eclipse (Import -> Existing Maven Projects).
4. Rode a aplicação (classe `LojazapLikeApplication`).

## Endpoints úteis
- POST `/auth/register` { username, password } -> retorna token
- POST `/auth/login` { username, password } -> retorna token
- GET `/products` -> listar produtos (público)
- POST `/products` -> criar produto (autenticado)
- GET `/cart` -> listar itens do carrinho (autenticado)
- POST `/cart/add` -> adicionar item ao carrinho (autenticado)
- POST `/orders/checkout` -> criar pedido a partir do carrinho (autenticado)

## Importante
- JWT secret em `application.properties` é `VerySecretJwtKeyChangeMe`. Troque em produção.
- Este projeto é um esqueleto funcional com as principais funcionalidades. Você pode estender o front-end e integrar gateways de pagamento conforme necessário.
