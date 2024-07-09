# Fullstack Developer

## Game Launcher Assignment

### Objective
Develop a backend in Java (using any framework) that functions as a Game Launcher.

### Example invoking a Game Launcher
The Game Launcher is an API endpoint that is used to trigger a new game session. Normally it processes GET request like this one 

```
curl 'http://localhost/play/{game-code}'
```

and redirect players to a game frontend (eg.
 `https://games.xciting.com/{game-code}?session={id}&language=en`)

### Requirements

Your Game Launcher should implement the following steps:

1. **Create a Session UUID**:
   - Generate a unique session UUID for each game launch.

2. **Persist Session Information**:
   - Save relevant information about the session and the game to be launched in a database. (use the db engine you think is better for this scenario).

3. **Redirect to Game URL**:
   - Redirect to a specified URL, injecting the created session UUID and the language parameter into the query parameters.

### Expected Behavior
- Your Game Launcher should be capable of creating sessions and redirecting to game URLs while persisting the necessary data.

### Error Handling
- Handle errors gracefully and return appropriate HTTP status codes.

---

## Back Office Assignment

### Objective
Using Node.js, implement a backend that renders a HTML page listing all games & sessions launched on the current day.

### Requirements
1. **Server-Side Rendering**:
   - Create a page that displays all games launched on the current day

2. **Data Display**:
   - Decide which data is relevant to display regarding the games launched.

### Error Handling
- Handle errors gracefully and return appropriate HTTP status codes.

---

### Important Notes
- Your solution will be evaluated based on code **readability**, **decision-making**, and **testing coverage**.
- It would be advantageous to deliver the code with Docker, but this is not mandatory.

---

Feel free to ask if you have any questions or need further clarifications. Good luck!