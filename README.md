# AhRepo2
# Bag Service API

A Spring Boot microservice for managing bag pickup/dropoff bookings, bag insurance, and tracking.

## Features

- Book bag pickups with contact details and location
- Book bag dropoffs with contact details and location
- Track bags using bag tag numbers
- Manage bag insurance, quotes and claims
- Validate bag details and flight information

## Tech Stack

- Java 11
- Spring Boot
- MySQL Database
- OpenAPI/Swagger Documentation
- JPA/Hibernate
- Maven

## Getting Started


### Prerequisites

- Java 11+
- MySQL 8.0+
- Maven

### Configuration

The application uses the following default configuration in `application.properties`:

- Server port: 7770
- Context path: `/api`
- MySQL database: `bagservice`
- Swagger UI: `/api/swagger-ui.html`
- API docs: `/api/api-docs`

### Running Locally

1. Clone the repository
2. Configure MySQL credentials in `application.properties`
3. Run `mvn clean install`
4. Start the application:
5. Once started, go to an API platform like Postman and send a request to the service. An example request for Booking a Bag Pickup would be:

    Request type: POST

    url: http://localhost:7770/api/v1/bags/pickup 

    body: Raw JSON

    body payload: see example pickup-booking-request.json in the project path src/main/resources/samples/
  