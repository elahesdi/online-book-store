# online-book-store


Welcome to the Online Book Store project! This project is an Online Book Store system developed using Spring Boot framework. It provides APIs for managing users, books, orders, inventory and authentication.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Authentication](#authentication)
- [Database Configuration](#database-configuration)

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/elahesdi/online-book-store.git
    ```

2. Navigate to the project directory:

    ```bash
    cd online-book-store
    ```

3. Install dependencies:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```

## Usage

After running the application, you can access the APIs using tools like Postman or cURL.

## Endpoints

Detailed information about the available endpoints can be found in the [Endpoints](./ENDPOINTS.md) document.

## Authentication

To access certain endpoints, you need to authenticate. Use the following endpoint to obtain a JWT token:

- **POST /auth/login**: Login with username and password to obtain JWT token.

## Database Configuration

Configure MySQL database with the following properties:

```properties
spring.datasource.password=
spring.datasource.username=
spring.datasource.url=
