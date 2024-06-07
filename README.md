# StayEase

## Introduction

StayEase is a RESTful API service built with Spring Boot for hotel room booking. It features user authentication and authorization, hotel management, and room booking functionalities.

## Features

- User Registration and Login
- Hotel Management
- Room Booking
- Role-based Access Control
- JWT Authentication
- Logging and Error Handling

## Endpoints

- `POST /api/auth/signup` - User registration
- `POST /api/auth/signin` - User login
- `GET /api/public/hotels` - List all hotels
- `POST /api/admin/hotels` - Create a hotel (Admin only)
- `PUT /api/manager/hotels/{hotelId}` - Update hotel details (Hotel Manager)
- `DELETE /api/admin/hotels/{hotelId}` - Delete a hotel (Admin only)
- `POST /api/customer/hotels/{hotelId}/book` - Book a room (Customer)
- `DELETE /api/manager/bookings/{bookingId}` - Cancel a booking (Hotel Manager)

## Local Setup

1. Clone the repository:
    ```shell
    git clone https://github.com/yourusername/stayease.git
    cd stayease
    ```

2. Update MySQL credentials in `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/stayease
    spring.datasource.username=root
    spring.datasource.password=password
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Build and run the application:
    ```shell
    mvn clean install
    java -jar target/stayease-0.0.1-SNAPSHOT.jar
    ```

4. Access the application at `http://localhost:8080`.

## Deployment on Heroku

1. Create Heroku app:
    ```shell
    heroku create stayease
    ```

2. Add MySQL database:
    ```shell
    heroku addons:create cleardb:ignite
    ```

3. Update `application.properties` with Heroku ClearDB credentials.

4. Deploy to Heroku:
    ```shell
    git push heroku master
    ```

5. Access the application at `https://stayease.herokuapp.com`.

## Postman Collection

[Public Postman Collection](#)

## License

MIT License
