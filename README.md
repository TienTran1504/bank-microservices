# Bank Microservices

A simple multi-module Java microservices project demonstrating a basic banking domain with separate services.

## Project Structure

- `accounts/` – Account service (customer accounts, balances, etc.)
- `cards/` – Card service (credit card information)
- `loans/` – Loan service (loan information)

Each microservice is a separate Spring Boot application.

## Prerequisites

- Java 21+ (adapt if your `pom.xml` uses a different version)
- Maven 3.8+  
- Git
- An IDE such as IntelliJ IDEA or VS Code (with Java support)

## Configuration

Each service has its own `application.properties` under:

- `accounts/src/main/resources/`
- `cards/src/main/resources/`
- `loans/src/main/resources/`

Common settings include:

- Server port
- Database connection (URL, username, password)
- Logging level

Adjust those files to point to your local or containerized databases.

## API Endpoints

Typical REST endpoints (exact paths may differ based on your controllers):

- **Accounts Service**
  - `api/v1/accounts/` – Accounts endpoints
- **Cards Service**
  - `api/v1/cards/` – Cards endpoints
- **Loans Service**
  - `api/v1/loans/` – Loans endpoints


## Maven Commands

|     Maven Command       |     Description          |
| ------------- | ------------- |
| "mvn clean install -Dmaven.test.skip=true" | To generate a jar inside target folder |
| "mvn spring-boot:run" | To start a springboot maven project |
| "mvn spring-boot:build-image" | To generate a docker image using Buildpacks. No need of Dockerfile |
| "mvn compile jib:dockerBuild" | To generate a docker image using Google Jib. No need of Dockerfile |

## Docker Commands

| Docker Command                                                                                                                |     Description          |
|-------------------------------------------------------------------------------------------------------------------------------| ------------- |
| "docker build . -t tientran1504/accounts:s4"                                                                                  | To generate a docker image based on a Dockerfile |
| "docker run  -p 8080:8080 tientran1504/accounts:s4"                                                                              | To start a docker container based on a given image |
| "docker images"                                                                                                               | To list all the docker images present in the Docker server |
| "docker image inspect image-id"                                                                                               | To display detailed image information for a given image id |
| "docker image rm image-id"                                                                                                    | To remove one or more images for a given image ids |
| "docker image push docker.io/tientran1504/accounts:s4"                                                                           | To push an image or a repository to a registry |
| "docker image pull docker.io/tientran1504/accounts:s4"                                                                           | To pull an image or a repository from a registry |
| "docker ps"                                                                                                                   | To show all running containers |
| "docker ps -a"                                                                                                                | To show all containers including running and stopped |
| "docker container start container-id"                                                                                         | To start one or more stopped containers |
| "docker container pause container-id"                                                                                         | To pause all processes within one or more containers |
| "docker container unpause container-id"                                                                                       | To unpause all processes within one or more containers |
| "docker container stop container-id"                                                                                          | To stop one or more running containers |
| "docker container kill container-id"                                                                                          | To kill one or more running containers instantly |
| "docker container restart container-id"                                                                                       | To restart one or more containers |
| "docker container inspect container-id"                                                                                       | To inspect all the details for a given container id |
| "docker container logs container-id"                                                                                          | To fetch the logs of a given container id |
| "docker container logs -f container-id"                                                                                       | To follow log output of a given container id |
| "docker container rm container-id"                                                                                            | To remove one or more containers based on container ids |
| "docker container prune"                                                                                                      | To remove all stopped containers |
| "docker compose up"                                                                                                           | Creates and starts containers based on the given Docker Compose file |
| "docker compose down"                                                                                                         | Stops and removes containers, networks, volumes, and images created by up |
| "docker compose start"                                                                                                        | Starts existing (previously created) containers without recreating them |
| "docker compose stop"                                                                                                         | Stops running containers without removing them |
| "docker run -p 3306:3306 --name accountsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=accountsdb -d mysql"                 | To create a MySQL DB container |
| "docker run -p 6379:6379 --name eazyredis -d redis"                                                                           | To create a Redis Container |
| "docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:22.0.3 start-dev" | To create Keycloak Container|