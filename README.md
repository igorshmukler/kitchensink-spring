# Kitchensink Spring Boot

This project is a basic example of a CRUD (Create, Read, Update, Delete) application developed with Spring Boot. It demonstrates how to use a combination of Spring Boot features, including Spring Data JPA, Hibernate, and validation, to build a simple web application that interacts with a database.

This project is a **Spring Boot** adaptation of the original **JBoss EAP Kitchensink Quickstart**. The goal was to modernize the application, converting it from JBoss EAP to Spring Boot while retaining the original functionality.

## Key Features

- **Spring Boot 3.x**
- **Spring Data JPA** for persistence using Hibernate
- **Spring MVC** for RESTful web services and form-based UI
- **Thymeleaf** for rendering web views
- **Validation** with **Jakarta Validation API**
- **H2** (in-memory database) for development and testing purposes
- **Maven** for dependency management and build automation

---

## Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven 3.6+**

### Running the Application

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-repo/kitchensink-springboot.git
   cd kitchensink-springboot
   ```

2. **Build the project:**

   ```bash
   mvn clean install
   ```

3. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

   The application will start on [http://localhost:8080](http://localhost:8080).

4. **Access the Application:**

   Visit [http://localhost:8080](http://localhost:8080) to interact with the member management system.

---

## REST API Endpoints

| HTTP Method | Endpoint        | Description                  |
|-------------|-----------------|------------------------------|
| `GET`       | `/members`      | Retrieve all members          |
| `GET`       | `/members/{id}` | Retrieve a specific member    |
| `POST`      | `/members`      | Create a new member           |
| `PUT`       | `/members/{id}` | Update an existing member     |
| `DELETE`    | `/members/{id}` | Delete a specific member      |

---

## Conversion Process (from JBoss EAP to Spring Boot)

This section describes the steps followed to convert the original **JBoss EAP Kitchensink** project into a **Spring Boot** project.

### Step 1: Project Setup

- Created a new Spring Boot project using Spring Initializr, selecting the following dependencies:
  - **Spring Web**
  - **Spring Data JPA**
  - **Spring Boot DevTools** (for hot reloading)
  - **Spring Boot Starter Thymeleaf**
  - **H2 Database** (in-memory database)

### Step 2: Dependency Migration

- Updated the `pom.xml` to include Spring Boot dependencies instead of the original JBoss/WildFly dependencies.
- Replaced `javax.*` packages with `jakarta.*` packages to comply with the Spring Boot 3.x requirement for Jakarta EE 9.

### Step 3: JPA Configuration

- Converted the JPA configuration to use **Spring Data JPA** with Hibernate, which is integrated with Spring Boot.
- Defined the `Member` entity and its validation using `jakarta.persistence` and `jakarta.validation.constraints`.
- Configured Spring Boot to use the **H2 in-memory database** for development purposes.

### Step 4: RESTful API and MVC Conversion

- Replaced the JAX-RS (RESTEasy) setup with **Spring MVC** for RESTful API endpoints.
- Created Spring controllers to handle web requests and defined methods for CRUD operations using Spring Data JPA.

### Step 5: Frontend (UI) Migration

- The original JSF-based frontend was replaced by **Thymeleaf** templates, which integrate seamlessly with Spring Boot.
- Created Thymeleaf templates for listing members, adding new members, and updating existing members.

### Step 6: Testing

- Configured Spring Boot’s **unit testing** framework for simple CRUD operation tests and validation.

### Key Differences Between JBoss EAP and Spring Boot

- **Deployment**: Unlike the JBoss EAP deployment model, Spring Boot packages the entire application as a self-contained JAR with an embedded Tomcat server. No need for deploying to an external application server.
- **Configuration**: Spring Boot’s auto-configuration mechanism simplifies setup, removing the need for manual configuration files (like `persistence.xml`).
- **Jakarta Packages**: With Spring Boot 3.x, the application uses Jakarta EE 9 APIs, so all `javax.*` imports were replaced with `jakarta.*`.

---

## Running the Application in Production

1. Build the JAR file:

   ```bash
   mvn clean package
   ```

2. Run the JAR file:

   ```bash
   java -jar target/kitchensink-springboot-0.0.1-SNAPSHOT.jar
   ```

---

## H2 Console

Spring Boot uses the H2 database for development by default. You can access the H2 console at:

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave blank)

---

## Further Reading

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Data JPA Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)

---

## License

This project is licensed under the MIT License.

---

## TODO

Add validations, unit tests, etc.

---

### Authors

- Original JBoss EAP Kitchensink contributors
- Spring Boot Conversion by ChatGPT 3.5 and Igor Shmukler <igor.shmukler@gmail.com>

---

This README provides a comprehensive guide for running the Spring Boot version of the Kitchensink application, while also documenting the steps taken to convert the original project from JBoss EAP to Spring Boot.

Let me know if you'd like any changes or additions to this document!