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


