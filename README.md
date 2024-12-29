Dating Suggestions Engine

- This is a Spring Boot application that facilitates matching users based on provided information. It includes functionalities to create users, retrieve matches, and display user data in a web-based interface.

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

API Endpoints

1. Get Top Matches
URL: /api/users/{id}/matches
Method: GET
Description: Retrieves the top matches for a user by their ID.
Response: Returns a list of user matches.
Example Request:
GET http://localhost:8080/api/users/2/matches

[
    {
        "id": 4,
        "name": "User 4",
        "gender": "Female",
        "age": 22,
        "interests": ["Watching Movies","Badmition","Skits"]
    },
    {
        "id": 5,
        "name": "User 5",
        "gender": "Female",
        "age": 22,
        "interests": [ "Singing"]
    }
]

2. Create User
URL: /api/users/create
Method: POST
Description: Creates a new user and saves it to the database.
Example Request:
POST http://localhost:8080/api/users/create
Content-Type: application/json

{
   "name": "User 1" ,
    "gender": "Female",
    "age": "25",
    "interests": ["Cricket ","Chess"]

}





