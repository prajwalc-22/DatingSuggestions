## Dating Suggestions Engine

This is a Spring Boot application that facilitates matching users based on provided information. It includes functionalities to create users, retrieve matches, and display user data in a web-based interface.

![Homepage Screenshot](images/homepage.png)

Features
- Create User: Add a new user to the system.
- View Matches: Retrieve and display top matches for a specific user.
- Web Interface: Interact with the application through web pages or Postman

Technologies Used
- Java: Programming language.
- Spring Boot: Framework for building the application.
- Spring MVC: Following MVC architecture.
- HTML,CSS,JAVASCRIPT: For rendering web pages.
- Spring Data JPA: For storing user data.
- Maven: Dependency management.

## API Endpoints

### 1. Get Top Matches
- **URL**: `/api/users/{id}/matches`
- **Method**: `GET`
- **Description**: Retrieves the top matches for a user by their ID.
- **Response**: Returns a list of user matches.

#### Example Request:

        GET http://localhost:8080/api/users/2/matches
       
Example Response:
   
       
        [
          {
            "id": 4,
            "name": "User 4",
            "gender": "Female",
            "age": 22,
            "interests": ["Watching Movies", "Badminton", "Skits"]
          },
          {
            "id": 5,
            "name": "User 5",
            "gender": "Female",
            "age": 22,
            "interests": ["Singing"]
          }
        ]
### 2. Create User
- **URL**: `/api/users/create`
- **Method**: `POST`
- **Description**: Creates a new user and saves it to the database.
- **Response**: Returns a list of user matches.

#### Example Request:

        POST http://localhost:8080/api/users/create
        Content-Type: application/json
Example Payload:

        {
           "name": "User 1" ,
            "gender": "Female",
            "age": "25",
            "interests": ["Cricket ","Chess"]
        }


## How to Run

Prerequisites
- Eclipse IDE for Enterprise Java and Web Developers
- Java JDK 17 or later installed
- Maven installed
- Spring Tools 4 (STS4) plugin
- Git installed

1. **Clone the repository**:
    - Open terminal or command prompt
    - Navigate to desired directory
    - Run the clone command:

      ```bash
   
       git clone <repository-url>
       cd <project-directory>

2. **Import Project in IDE:**:
   - Go to File → Import
   - Select Maven → Existing Maven Projects
    
   

3. **Configure Application Properties:**:
   - Navigate to src/main/resources/application.properties
   - Update necessary configurations:
        - Database connection (if required)
        - Server port (default is 8080)
  
4. **Build the Project:**:
    - Using IDE:
      - Eclipse: Right-click project → Run As → Maven Build → Goals: clean install
    - Using Command Line:

      
        ```bash
        # For Maven
        mvn clean install

5. **Run the Application:**:
    - Using IDE:
           Locate the main class (usually contains @SpringBootApplication annotation)→ Run 'MainClass'
    - Using Command Line:

      ```bash
      # For Maven
       mvn spring-boot:run
   - Visit: http://localhost:8080

## How to Test the Application
- Use Postman or a similar tool to test API endpoints.
- Navigate to the provided URLs in your browser for accessing the web pages.
