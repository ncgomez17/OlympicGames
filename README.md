# OlympicGames
Spring boot Project with React components created on Qindel Group.It is a test application to get experience with new technologies.This application allows you to manage different entities of an Olympic Games organization.
### Main Entites

- Countries
- Cities
- Headquarters
- Olympic Games types

### Technologies and Architecture
- Spring JPA
- Spring AOP
- Spring Security and JWT
- Spring MongoDB
- OpenAPI 3.0
- React Hooks
- React functional components

### Functionality
- CRUD of the different entities
- Log record with information on the execution of all the methods of the project,using Spring AOP
- User authentication with Spring Security JWT
- Geolocation of cities
- Audit of entities, saving the information of these in the Mongodb database.

### Project execution

#### Previous
- MySQl and MongoDB server
- Maven(version >3.5.x)
- (optional)IDE


Create BD juegosolympicos in MySQl and MongoDB and create user nicolas with password nicolas in both databases.

Install Node.js >16.8

#### Executing:
Go to the directory juegos_olympicos and execute

`mvn install`

`mvn spring-boot:run`

Go to the directory juegos_olympicos_Front and use 

`npm install`

`npm start`




  






