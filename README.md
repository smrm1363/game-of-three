# Game of three JVM Take Home Test

The goal is to implement a game with two independent units – the players –
communicating with each other using an API.

When a player starts, it incepts a random (whole) number and sends it to the
second
player as an approach to starting the game. The receiving player can now
always choose between adding one of {-1, 0, 1} to get to a number that is
divisible by 3. Divide it by three. The resulting whole number is then sent back
to the original sender.
The same rules are applied until one player reaches the number 1(after the
division).

### Tech stack&Architecture

- Java 17
- Spring Boot
- REST API
- Docker
- Gradle
- Hexagonal Architecture

## How to Run

### Prerequisites

- Java Development Kit (JDK) installed
- Docker installed

### Build and Run
There are two ways to build and run the application, the first and easier way would be just using Gradle without docker, the second would more comprehensive way including Docker(with the implicit way of Spring-Boot without using docker file)

#### The simple way
1. **Build and run the project in one step:**

      ```bash
      ./gradlew bootRun

#### Including Docker

1. **Build the Project:**

   ```bash
   ./gradlew build

2. **Build the Docker image:**

   ```bash
   ./gradlew bootBuildImage
3. **Run the Docker image:**

   ```bash
   docker run -p 8080:8080 docker.io/library/game-of-three:0.0.1

Replace the ports with the free ones of your system if needed.



### How to Run Tests
Use the following command to run all tests:

    ./gradlew test


#### Open-API/Swagger UI
You can access Open-API/Swagger UI documentations via following links:

http://localhost:8080/swagger-ui/index.html

http://localhost:8080/v3/api-docs

http://localhost:8080/v3/api-docs..yaml


## Additional Notes

Indeed, this is just a sample project and not a very comprehensive one. While it provides basic functionality for the testing porous:

- **Test Coverage:** While there are basic unit tests, the test coverage is not extensive. Additional unit tand integration tests could be added to cover more edge cases and scenarios.
- **Error Handling:** The error handling in the application could be enhanced to provide more informative error messages and handle a wider range of potential errors. I tried to provide very basic requirements here.
- **Security:** Security measures such as authentication and authorization could be implemented to secure the API endpoints and prevent unauthorized access.
- **Logging:** More sophisticated logging mechanisms could be implemented to provide better visibility into the application's behavior and facilitate troubleshooting. Since it was just a test in a limited time, I did not add the put the logs.
- **Performance Optimization:** The application's performance could be optimized further.
- **Adding docker file:** The application itself is implicitly dockerized, thanks to the Spring boot framework recent features, however, there will be the possibility to add docker file to have better configuration of it.

Despite these potential areas for improvement, the project serves as a demonstration of basic test application.
