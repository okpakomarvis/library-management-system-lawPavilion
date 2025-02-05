# Library Management System LawPavilion

## Introduction
The Library Management System is a comprehensive application that enables users to add, view, edit, search, and delete book records. It comprises a JavaFX-based,SceneBuilder frontend for the user interface and a Spring Boot backend to handle business logic and data storage.

### Features
* Add Books: Users can add new books to the library collection.
* View Books: Display a list of all books in the library.
* Edit Books: Modify details of existing books.
* Delete Books: Remove books from the library.
* Search Books: Search for books from the Library

### Prerequisites
Before setting up the application, ensure you have the following installed:
* Java Development Kit (JDK) 11 or higher: Download JDK
* Apache Maven: Download Maven
* Git: Download Git
* IntelliJ : Download IntelliJ (optional)

### Backend Setup (Spring Boot)
1. Clone the Library Repository:
    ```bash
    git clone https://github.com/okpakomarvis/library-management-system-lawPavilion.git
   cd library-management-system-lawPavilion
    ```
2. Build the Application:
     ```bash
    mvn clean install
    ```
3. Run the Application:
   Build the Application:
     ```bash
    mvn spring-boot:run or ./mvnw spring-boot:run
    ```
   The backend server will start at http://localhost:8080

### Frontend Setup (JavaFX)
1. cd to Frontend folder:
      ```bash
    cd frontend
    ```
2. Build the Application:
      ```bash
    mvn clean install
    ```
3. Run the Application:
  ```bash
  mvn javafx:run or you can run it from the main class LibraryApplication.java
   ```
The JavaFX application window will launch, allowing interaction with the library system.

### Usage
* Add a Book: Enter the book's Title, Author, ISBN, and Published Date in the respective fields and click the "Insert" button.
* View Books: All books are displayed in the TableView upon launching the application or clicking the "Refresh" button.
* Edit a Book: Select a book from the TableView, modify the desired fields, and click the "Update" button.
* Delete a Book: Select a book from the TableView and click the "Delete" button.
* Search a Book: Enter the book's Title or Author , on the search field, it will automatically search for the data and update the table.

### List of APIs
- fetch all Books with pagination {?page=0&size=5}
  GET: http://localhost:8080/api/books
- create a book
  POST: http://localhost:8080/api/books
- fetch a book by {isbn}
  GET: http://localhost:8080/api/books/{isbn}
- update a book by {isbn}
  PUT: http://localhost:8080/api/books/{isbn}
- search for books by query parameter {?query=text&page=0&size=5}
  search: http://localhost:8080/api/books/search?query=text
- Delete a book by {id}
  Delete: http://localhost:8080/api/books/{id}


