# online-book-store


Welcome to the Online Book Store project! This project is an Online Book Store system developed using Spring Boot framework and MySQL as database. It provides APIs for managing users, books, orders, inventory and authentication.

Admin Have Following Access for this online store site:

- Add New Books.
- View Books Available and sort them (pagination).
- Remove Books.
- Increase Books Amount.
- view orders.
- Update Users.
- Delete Users.
- view all Users.

Users Have Following Access for this online store site:
- Create New Account or Register.
- Login.
- View Available Books.
- Select Books and Quantity to Buy.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Swagger](#swagger)
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

## Swagger
swagger endpoint: ${hostname}:7878/swagger-ui/

## Database Configuration

Configure MySQL database with the following properties:

```properties
spring.datasource.password=
spring.datasource.username=
spring.datasource.url=


