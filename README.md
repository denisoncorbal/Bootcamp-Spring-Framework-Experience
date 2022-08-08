# Cloud Parking


## Run database
docker run --name vehicle-db -p 5432:5432 -e POSTGRES_DB=vehicle -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -d postgres:10-alpine

## Start and Stop

### Stop Database
docker stop vehicle-db

### Start Database
docker start vehicle-db
