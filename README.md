# GitHub Repositories Reader
A simple Spring application that exposes a REST endpoint to fetch non-fork GitHub repositories of a given user. For each repository, it also returns branches and the latest commit SHA.

## Features 

- Fetch all GitHub repositories of a user that are not forks
- For each repository, the following is returned:
  - Repository name
  - Owner login
  - List of branches with:
    - Branch name
    - SHA of the latest commit
- Handles the case when a GitHub user is not found (404)

## Technologies

- Java 21
- Spring Boot 3.5.0

## Usage

How to Run the Project (Windows):
- Install Maven (if not already installed):
    - Download Maven from:
      https://maven.apache.org/download.cgi
      (choose the binary zip archive)
    - Extract the archive
    - Copy the path to the bin folder inside the extracted directory
    - add the maven to system variables
      - go to "Edit the system environment variables"
      - go to "Environment variables"
      - on "System variables" section, press 2 times on path
      - press on a empty row and paste the copied path
      - clik ok and close
- Run the application:
    - Open your terminal in any folder and run:
      ```bash
      git clone https://github.com/MatDawidowski/github-user-repos.git
      cd github-user-repos
      mvn spring-boot:run
      ```
  Access the API:
    - Open your browser and go to: http://localhost:8080/api/github/<github_username>/repositories

How to RUn the Project (Linux):
- Install Maven (if not already installed):
    - open terminal and write
    ```bash
    sudo apt install maven
    ```
- Run the application:
    - Open your terminal in any folder and run:
      ```bash
      git clone https://github.com/MatDawidowski/github-user-repos.git
      cd github-user-repos
      mvn spring-boot:run
      ```
   Access the API:
    - Open your browser and go to: http://localhost:8080/api/github/<github_username>/repositories
     
