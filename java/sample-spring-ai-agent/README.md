# AI Agent implementation with Spring Boot framework

This project contains AI Agent implementation example with Spring AI. You'll need basic knowledge of Java before you can start exploring this project.

## Steps to run server locally

Before you start the server, please start the MySQL server.

To run the server locally -
`mvn spring-boot:run`

You dont need to run Tomcat server seperately to run this project. Spring Boot run embedde tomcat server.

Access the API -
```sh
curl --request POST \
  --url http://localhost:8080/chat \
  --data '{
	"message": "Hi, how are you ?"
}'
```

## Unit tests

This project does not include unit tests yet.
