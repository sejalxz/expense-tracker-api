# Expense Tracker API
Expense Tracker API is a backend service for managing expenses. To run this API, you need to configure the application.properties file with your database connection details and set up environment variables for PostgreSQL. This README will guide you through the necessary steps to get the API up and running using Docker Compose.

## Prerequisites
Before setting up and running the Expense Tracker API, make sure you have the following prerequisites installed on your system:

1. Docker: You can download and install Docker from [Docker's official website](https://www.docker.com/get-started/).

2. Docker Compose: Docker Compose typically comes bundled with Docker. Ensure it's available on your system.

## Configuration
#### 1. application.properties
   Create or modify the application.properties file to configure your database connection details. This file is located in the application's source code, usually in the src/main/resources directory. You can use a text editor to create or edit this file.

Here are the properties you need to set in application.properties:
``` 
spring.datasource.url=jdbc:postgresql://<YOUR_DATABASE_URL>:<PORT>/<YOUR_DATABASE_NAME>
spring.datasource.username=<YOUR_DATABASE_USERNAME>
spring.datasource.password=<YOUR_DATABASE_PASSWORD>
spring.datasource.driver-class-name=org.postgresql.Driver
``` 
Replace <YOUR_DATABASE_URL>, <PORT>, <YOUR_DATABASE_NAME>, <YOUR_DATABASE_USERNAME>, and <YOUR_DATABASE_PASSWORD> with your specific PostgreSQL database details.

#### 2. Environment Variables
   Set the following environment variables to provide PostgreSQL credentials. You can do this using your terminal:

``` 
POSTGRES_USER=<YOUR_POSTGRES_USER>
POSTGRES_PASSWORD=<YOUR_POSTGRES_PASSWORD>
POSTGRES_DB=<YOUR_POSTGRES_DB>
```
Replace <YOUR_POSTGRES_USER>, <YOUR_POSTGRES_PASSWORD>, and <YOUR_POSTGRES_DB> with your PostgreSQL credentials.

#### 3. Running the Expense Tracker API
After configuring the **application.properties** file and setting the environment variables, you can run the Expense Tracker API using Docker Compose.

1. Navigate to the root directory of the Expense Tracker API project, where your **docker-compose.yml** file is located.

2. Open a terminal window.

3. Run the following command to start the API service in the background (detached mode):

``` 
docker compose up -d
```
This command will start the API service and PostgreSQL database in the background, and your Expense Tracker API will be accessible at the specified URL and port.



### Accessing the API
You can access the API using the specified URL and port, which you configured in the application.properties file. The API will be available at **http://<YOUR_API_URL>:<PORT>**.

That's it! You have successfully set up and run the Expense Tracker API. You can now start making API requests and managing your expenses.




