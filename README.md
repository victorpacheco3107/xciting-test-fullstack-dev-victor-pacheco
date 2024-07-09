# Fullstack Game Launcher and Back Office Application

## Overview

This project is a fullstack application consisting of two main components: a Game Launcher backend developed in Spring Boot and a Back Office application developed in Node.js. Both applications share a PostgreSQL database and are containerized using Docker.

## Features

### Spring Boot - Game Launcher

- **Hexagonal Architecture**: Implements a modular architecture that separates the core business logic from external components.
- **Create a Session UUID**: Generates a unique session UUID for each game launch.
- **Persist Session Information**: Saves relevant information about the session and the game to be launched in a PostgreSQL database.
- **Redirect to Game URL**: Redirects to a specified URL, injecting the created session UUID and the language parameter into the query parameters.
- **Error Handling**: Gracefully handles errors and returns appropriate HTTP status codes.
- **Test Coverage**: Achieves 90% test coverage using JUnit and Mockito for unit testing.

### Node.js - Back Office

- **Server-Side Rendering**: Renders an HTML page listing all games and sessions launched on the current day.
- **Data Display**: Displays relevant data about the games launched.
- **Responsive Design**: Utilizes Bootstrap to ensure the interface is responsive and user-friendly.
- **Pagination and Filtering**: Provides server-side pagination and filtering functionality for efficient data management.
- **Error Handling**: Gracefully handles errors and returns appropriate HTTP status codes.
- **Test Coverage**: Includes unit tests with Jest for robust test coverage.

## Docker Setup

The project includes a `docker-compose.yml` file that defines the services for the Spring Boot application, the Node.js application, and the PostgreSQL database.

### Services

- **Game Launcher (Spring Boot)**:
  - Dockerfile located in the `gamelauncher` directory.
  - Uses Java 17.
  - Runs on port 80.

- **Back Office (Node.js)**:
  - Dockerfile located in the `backoffice-assignment` directory.
  - Uses Node.js version 20.
  - Runs on port 3000.

- **PostgreSQL Database**:
  - Uses the official PostgreSQL image.
  - Database name: `postgres`
  - User: `postgres`
  - Password: `postgres`
  - Runs on port 5432.

### Running the Containers

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. **Set up environment variables**:
   Ensure you have a `.env` file at the root level with the following content:
   ```env
   DB_USER=postgres
   DB_PASSWORD=postgres
   DB_HOST=postgres
   DB_NAME=postgres
   DB_PORT=5432
   PORT=3000
   ```

3. **Build and run the containers**:
   ```bash
   docker-compose up --build
   ```

4. **Access the applications**:
   - Game Launcher: [http://localhost/play/{game-code}](http://localhost/play/{game-code})
   - Back Office: [http://localhost:3000/games](http://localhost:3000/games)

### Stopping the Containers

To stop the running containers, use the following command:
```bash
docker-compose down
```

## Notes

- The Game Launcher API endpoint can be tested using tools like `curl` or Postman.
- The Back Office application can be accessed via a web browser and provides a paginated, searchable view of the games launched on the current day.
- Ensure that Docker and Docker Compose are installed on your machine before running the setup commands.

