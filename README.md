# 📚 Project: Library Book Management

## 📋 Project Description

This web application was developed for a local library to digitize the tracking of books and readers. The application allows librarians to:

- Register new readers
- Add, edit, and delete books
- Assign books to readers and release books when returned
- View lists of all books and all readers
- See which books are assigned to which readers

---

## 🛠️ Technologies Used

| Technology | Purpose |
|:-----------|:--------|
| **Java 17+** | Main programming language |
| **Spring MVC** | Building the web application following the MVC pattern |
| **Spring JDBC (JdbcTemplate)** | Database interaction |
| **Thymeleaf** | Template engine for creating HTML pages |
| **PostgreSQL** | Relational database for storing information about books and readers |
| **Apache Tomcat** | Web server for deploying the application |
| **Maven** | Project build tool and dependency manager |
| **HTML + CSS** | Building the user interface |

Additional:
- Data validation using `@Valid` and Spring Validator.
- DAO pattern is applied for database access.
- UTF-8 encoding is configured for proper support of the Russian language.

---

## 🗃️ Project Structure

- **config/** – Spring configuration classes (`SpringConfig`, `MySpringMvcDispatcherServletInitializer`)
- **controllers/** – Controllers for managing page logic (`PeopleController`, `BooksController`)
- **DAO/** – Data Access Objects (`PersonDAO`, `BookDAO`, `BookMapper`)
- **models/** – Data models (`Person`, `Book`)
- **views/** – HTML templates (using Thymeleaf)

---

## 🛠️ Main Functionality

- **Managing readers:**
  - View a list of all readers (`/people`)
  - Add a new reader (`/people/new`)
  - Edit a reader (`/people/{id}/edit`)
  - Delete a reader

- **Managing books:**
  - View a list of all books (`/books`)
  - Add a new book (`/books/new`)
  - Edit a book (`/books/{id}/edit`)
  - Delete a book

- **Book-reader relationship:**
  - Assign a book to a reader (if the book is free)
  - Release a book (when returned)

- **Additional features:**
  - On the book page, show who the book belongs to or if it is free
  - On the reader page, show the list of books assigned to the reader

---

## 📂 How to Run the Project

**Create the database:**

```sql
CREATE DATABASE project1;
CREATE TABLE Person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    birthdate INT
);

CREATE TABLE Book (
    book_id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    author VARCHAR(255),
    year INT,
    person_id INT REFERENCES Person(id) ON DELETE SET NULL
);
``` 
---

## ⚙️ Configure Database Connection

**In `SpringConfig.java`:**

- **URL:** `jdbc:postgresql://localhost:5432/project1`  
- **Username / Password:** `postgres / postgres`  
- **Driver:** `org.postgresql.Driver`

---

## 🚀 Start the Application

1. **Import the project** into your IDE (e.g., IntelliJ IDEA)
2. **Build the project** using Maven
3. **Deploy** it to an Apache Tomcat server
4. Open a browser and go to:  
   👉 `http://localhost:8080/`

---

## ⚡ Notes

- The project can be improved by integrating **Spring Data JPA** instead of plain JDBC.
- For more powerful validation, consider using **Hibernate Validator**.
- The application uses a **multi-page architecture** with dynamic UI generation via **Thymeleaf**.

---

