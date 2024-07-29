# Star Wars Planets Management API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

## Descrição
A API tem a funcionalidade de fazer o gerenciamento de planetas da saga Star Wars, podendo adicionar, listar e remover planetas.

Foi construida com Java 21, Spring Boot 3.2.2 e faz uso da api "Swapi" uma api pública do Star Wars.

Fiz essa api com o intuido de estudar mais sobre APIs com um desafio.
Esse projeto foi feito seguindo os requisitos do [challenge-back-end-hit](https://github.com/AmeDigital/challenge-back-end-hit) da
empresa Ame.

Link da API usada para buscar a quantidade de aparições de cada planeta na saga: https://swapi.dev/

## Instrução de instalação
### Pré requisitos

- Docker
- Docker Compose
- Git 2.4 (necessário apenas para clonar o repositório)

## Instrução de uso
O projeto usa Docker e Docker Compose, portanto para rodar a aplicação basta ter eles instalados e subir os containers necessários.
1. Entre no diretório ``docker-compose``
2. Execute o seguinte comando do Docker compose:
```dockerfile
docker-compose up
```
3. Acesse o endpoint do Swagger para visualizar a documentação da api:
```text
http://localhost:8080/swagger-ui/index.html
```
