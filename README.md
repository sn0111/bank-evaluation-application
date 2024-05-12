# Bank Property Evaluation Application


## Backend (Spring Boot):

**Requirements:**

* Java 17 or later (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* Maven (https://maven.apache.org/download.cgi)
* PostgreSQL database server (https://www.postgresql.org/download/)

**Technologies:**

* **Spring Boot:** Provides a rapid application development framework for backend services.
* **Spring Data JPA:** Simplifies database interactions using JPA entities and repositories.
* **PostgreSQL:** Stores application data using a relational database.
* **Spring Security:** Enforces user authentication and authorization.
* **Swagger:** Generates interactive API documentation (https://swagger.io/)

**Key Features:**

* **Database Schema Creation:** Spring Data JPA automatically creates database tables based on your entity classes.
* **Audit Trail:** Maintained through separate audit tables for comprehensive logging of task changes and user actions.
* **Spring Security with Basic Authentication:** Provides secure user access through username and password credentials.
* **Global Exception Handling:** Handles exceptions gracefully and returns custom responses with appropriate status codes.
* **Swagger Documentation:** Explore the API in detail using the comprehensive **[Swagger Documentation](http://localhost:8080/swagger-ui.html)** (likely URL).
* **Service Logs:** Implemented logging to maintain service logs in files according to the date. We achieved this by configuring the Logback logging framework, which is commonly used with Spring applications
* **Spring Gateway:** Acts as an entry point for all incoming requests, allowing for routing, filtering, and handling cross-cutting concerns in a microservices architecture.
* **Spring Cloud Config:** Provides centralized configuration management for distributed systems, allowing configurations to be stored externally and accessed by multiple services.
* **Eureka Server:** Acts as a service registry in a microservices architecture, allowing services to register themselves and discover other services without hardcoding their locations.
* **High Availability:** Achieved through strategies like service redundancy, fault tolerance, and load balancing, ensuring continuous availability of services even in the face of failures.
* **Horizontal Scaling:** Enables the system to handle increased loads by adding more instances of services and distributing the load across them, thereby improving performance and scalability. This can be achieved dynamically through auto-scaling mechanisms or manually by adding more resources as needed.
* **Caching & Scheduling:** Cache the seeded/master data and handle with scheduling jobs.
### Running the Backend

**Steps to run services**

1. Start the Eureka server to register all services.
2. Start the Config server service to load external config properties.
3. Start the Gateway server.
4. Start the Evaluation service.
5. Access Swagger using the URL http://localhost:8085/pv/swagger-ui/index.html

## Frontend (Angular):

1. The Bank Property Evaluation application has two screens.
2. It collects details to create property evaluations, implemented across multiple individual components.
3. There's a view screen to verify/approve property evaluation requests.
4. A simple login page is provided to access the application.

## Camunda Workflow:

1. Camunda for a streamlined workflow design to automate the property evaluation process. Camunda offers powerful features for orchestrating business processes, including task management, decision automation, and process monitoring.
2. Initiated the workflow with a "First Login" user task to authenticate users, ensuring secure access to the system. Leveraged Camunda's service task with HTTP connectors to seamlessly connect to external services for user validation.
3. Utilized custom forms to capture essential details for creating new property evaluations. These forms enhance user experience and streamline data input, ensuring accuracy and completeness in the evaluation process.
4. Integrated service delegates within the workflow for seamless data conversion and API calls. Service delegates enable interaction with external systems and execution of business logic, ensuring smooth workflow execution and data flow throughout the evaluation process.

