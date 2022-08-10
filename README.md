# Cloud Parking

## Acréscimos ao projeto
- Suporte a criação de mais de um estacionamento
- Suporte para cadastro de estacionamento com registro de limite de capacidade de veículos
- Adicionada regra de negócio para não permitir que um mesmo veículo estacione novamente com estacionagem anterior pendente
- Adicionada regra de negócio para não permitir novas estacionagens quando o limite de capacidade do estacionamento for atingido
- Adicionados testes para plataforma Windows de forma local com banco em memória H2


## Run database
docker run --name vehicle-db -p 5432:5432 -e POSTGRES_DB=vehicle -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -d postgres:10-alpine

## Start and Stop

### Stop Database
docker stop vehicle-db

### Start Database
docker start vehicle-db
