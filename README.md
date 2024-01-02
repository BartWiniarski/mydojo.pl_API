# MyDojo Backend (REST API) - Version 0.1.0

## Overview

The MyDojo Backend, version 0.1.0, is a RESTful API developed to support the MyDojo web application. This version establishes the groundwork for a comprehensive martial arts school management system, catering to the needs of administrators, trainers, and students.

## Version 0.1.0 Features

This initial release includes essential functionalities:

- **User Management**: Admins can manage user accounts, including creating, updating, deleting, and changing statuses.
- **Training Group Management**: Features for creating, updating, and deleting training groups.
- **Venue Management**: Admins can handle details regarding venues for scheduling purposes.
- **Schedule Management**: Functionality for managing training schedules.
- **Security**: Incorporates JWT-based authentication and role-based authorization.
- **User Profile Management**: Users can view and update their profiles.
- **Training Group Access for Students and Trainers**: Customized access to training group information based on user roles.

## Technical Details

### Core Technologies and Frameworks

- **Java 17**: The latest long-term support version of Java, offering high performance and a host of modern features for building robust applications.
- **Spring Boot 3.2.0**: This version of Spring Boot simplifies the setup and development of new Spring applications. It is specifically tailored for developing stand-alone, production-grade applications.
- **Hibernate (Spring Boot Starter Data JPA)**: Used for ORM (Object-Relational Mapping), Hibernate simplifies database interactions, allowing seamless and efficient data manipulation and retrieval.
- **Spring Security**: Provides a robust security framework that handles authentication and authorization in the application.
- **JSON Web Tokens (JWT)**: Used for stateless authentication in the application, enabling secure communication between the client and server.
- **Spring Web (Spring Boot Starter Web)**: Facilitates the creation of web applications with features such as RESTful services and JSON serialization/deserialization.

### Key Dependencies

- **MySQL Connector/J**: JDBC driver for MySQL, allowing the application to connect to a MySQL database for data storage.
- **Lombok**: A Java library that automatically plugs into the editor and build tools, simplifying the code by generating boilerplate code like getters, setters, and constructors.
- **JJWT (Java JWT)**: A Java library for creating and verifying JSON Web Tokens, used for secure user authentication.
- **Spring Boot DevTools**: Provides fast application restarts, LiveReload, and configurations for enhanced development experience.
- **Spring Boot Starter Test**: Offers utilities for testing Spring Boot applications with libraries like JUnit, Hamcrest, and Mockito.
- **JavaFaker**: A library for generating fake data, useful for testing and development.

### Build Tools

- **Maven**: Used for project management and build automation. It simplifies the process of building and managing the project dependencies.

## Security Configuration

### JWT (JSON Web Token) Authentication

- **Token-Based Authentication**: The system uses JWT for stateless authentication. It generates access and refresh tokens for authorized users, ensuring secure communication between the client and the server.
- **Access and Refresh Tokens**:
    - **Access Token**: A short-lived token (specified by `accessTokenExpiration`) used for regular authentication and authorization of requests.
    - **Refresh Token**: A longer-lived token (specified by `refreshTokenExpiration`) used to generate a new access token once the original expires. This ensures continuous user authentication without requiring frequent logins.
- **Token Generation and Validation**:
    - **Generation**: Tokens are generated using the `JwtService`, which encodes user details and the expiration time into each token.
    - **Validation**: The `JwtAuthenticationFilter` checks the validity of access tokens with each request, authenticating the user and attaching them to the security context.
- **User-Specific Tokens**: Tokens are created specific to user accounts, with the `JwtService` extracting user details from the token to facilitate user-specific processing.
- **Exception Handling**: The system handles exceptions such as expired tokens, returning appropriate error messages and HTTP status codes.

### Security Filters and Authentication Management

- **JwtAuthenticationFilter**: This filter intercepts requests to validate JWTs. It parses the token, validates it, and sets the user authentication in the security context.
- **AuthenticationService**:
    - **Registration and Authentication**: Handles user registration and authentication, generating access and refresh tokens upon successful authentication.
    - **Token Refresh**: Provides functionality to refresh access tokens using the provided refresh token.
- **Exception Handling**: Customized responses are implemented for scenarios like disabled users and bad authentication attempts.

### Password Encoding and Role Management

- **BCrypt Password Encoder**: The system uses BCrypt for hashing user passwords, ensuring password security in the database.
- **Role-Based Access Control**: Endpoints are secured based on user roles like ADMIN, TRAINER, and STUDENT, with Spring Security configurations defining access controls.

### CORS Configuration

- **Cross-Origin Resource Sharing**: Configured to allow requests from specified origins, ensuring that only authorized front-end applications can interact with the backend.

### Additional Security Measures

- **Spring Security**: Further reinforces the security of the application with features like protection against CSRF attacks and session fixation.
- **Database Security**: Uses encrypted connections and secure practices for database access and manipulation.

### Key Dependencies for Security

- **io.jsonwebtoken**: Libraries (`jjwt-api`, `jjwt-impl`, `jjwt-jackson`) used for creating and validating JWTs.
- **Spring Boot Starter Security**: Provides the necessary dependencies for integrating Spring Security into the application.

This security setup ensures a robust and secure environment for handling authentication, authorization, and data integrity in the MyDojo Backend application.

### REST Controllers

The application includes several controllers for different functionalities:

1. **AuthenticationController**: Handles user authentication and registration.
2. **AdminController**: Manages administrative tasks.
3. **StudentController**: Handles student-related functionalities.
4. **TrainerController**: Manages trainer-specific operations.
5. **UserController**: Responsible for general user profile operations.

### Error Handling

Implemented custom responses for better clarity in cases of authentication failures or access issues.

## API Endpoints

The API is structured under the base path `v1`, with various endpoints categorized by their functionality. Below is a detailed description of each endpoint:

### Authentication Endpoints

- **POST /v1/auth/register**
    - Registers a new user.
    - Request Body: User registration information.
    - Response: Access token and registration confirmation.
- **POST /v1/auth/authenticate**
    - Authenticates a user and generates an access token.
    - Request Body: User login credentials.
    - Response: Access token, refresh token, and user details.
- **POST /v1/auth/refresh**
    - Refreshes the access token using the refresh token.
    - Request Header: Refresh token.
    - Response: New access token.

### Admin Endpoints

- **GET /v1/admin/users**
    - Retrieves a list of all users (ADMIN only).
    - Response: List of user details.
- **POST /v1/admin/users**
    - Adds a new user (ADMIN only).
    - Request Body: New user details.
    - Response: Confirmation of user creation.
- **GET /v1/admin/users/{id}**
    - Retrieves details of a specific user (ADMIN only).
    - Path Variable: User ID.
    - Response: User details.
- **PUT /v1/admin/users/{id}**
    - Updates an existing user (ADMIN only).
    - Path Variable: User ID.
    - Request Body: Updated user details.
    - Response: Confirmation of user update.
- **DELETE /v1/admin/users/{id}**
    - Deletes a specific user (ADMIN only).
    - Path Variable: User ID.
    - Response: Confirmation of user deletion.

### Trainer Endpoints

- **GET /v1/trainer/trainingGroups**
    - Retrieves training groups managed by the trainer.
    - Response: List of training groups.

### Student Endpoints

- **GET /v1/student/trainingGroups**
    - Retrieves training groups associated with the student.
    - Response: List of student's training groups.

### General User Endpoints

- **GET /v1/users/profile**
    - Retrieves the profile of the currently authenticated user.
    - Response: User profile details.
- **PUT /v1/users/profile**
    - Updates the profile of the currently authenticated user.
    - Request Body: Updated profile details.
    - Response: Confirmation of profile update.

### Other Endpoints

- Additional endpoints for managing training groups, venues, schedules, etc., follow a similar structure and are secured based on user roles.

## License

This project is licensed under the [License](LICENSE.md).

## Contact

For further information or support, contact [support@mydojo.pl](mailto:support@mydojo.pl).

---

**Note**: This README reflects the features and setup for version 0.1.0 of the MyDojo Backend and will evolve with future updates and features.
