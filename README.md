# DataCosmosProject_Lastest

# Description
This project is a website designed to retrieve datasets based on user-provided keywords. It is built using the Spring Boot framework, MySQL for data storage, and Postman for testing the API endpoints.

The website allows users to search for datasets by entering keywords related to their desired data. The application then retrieves relevant datasets link from the specific website link and presents them to the user. Users can view the details of each dataset, such as its name, description, and source.

# Features

- User registration and login: Users can create an account and log in to the website.
- Dataset search: Users can enter keywords to search for datasets.
- Dataset retrieval: The application retrieves datasets from the database based on the provided keywords.
- Dataset details: Users can view the details of each dataset, including its name, description, and source.

# Technologies Used

Spring Boot: Java framework for building the web application.
MySQL: Relational database management system used for storing the datasets.
Postman: Tool for testing the API endpoints.
Selenium: For the web crawl.

# Setup Instructions

1. Clone the project repository from GitHub.
2. Set up the MySQL database and create the necessary tables.
3. Update the database configuration in the application.properties file.
4. Build and run the project using Maven or an IDE.
5. Access the website using a web browser.

# API Endpoints

The project exposes the following API endpoints:

- POST /api/register: Register a new user.
- POST /api/login: Log in an existing user.
- GET /api/datasets: Retrieve datasets based on keywords.




