# 📚 Library Management System

A **Library Management System** built with **Java Spring Boot** and a relational database to manage books, members, system users, and borrowing transactions.  
This project was developed as part of a technical challenge to demonstrate skills in backend development, database design, role-based access control, and RESTful API design.

---

## 🚀 Project Overview

The Library Management System provides a secure and efficient way to:

- Manage **books** with extended metadata (authors, publishers, categories, language, publication year, ISBN, edition, summary, cover images).
- Manage **members** (borrowers) with personal and membership details.
- Manage **system users** (Administrator, Librarian, Staff) with **role-based access control**.
- Handle **borrowing and return transactions** with clear activity tracking.
- Enforce **secure authentication and authorization** using Spring Security.
- Track **user activities** such as book creation, updates, deletions, borrowing, and returning, with detailed logs.

The system is designed to be **scalable, secure, and extensible**, following clean architecture principles and best practices for modern Spring Boot applications.

---
## ✨ Features

- **Book Management**
  - Add, update, delete, and view books.
  - Support for multiple authors per book.
  - Category/genre classification with hierarchical structure.
  - Publisher metadata, publication year, edition, ISBN, summary, and cover images.

- **Member Management**
  - CRUD operations for library members.
  - Track borrowed and returned books per member.

- **System User Management**
  - Three roles implemented:
    - **Administrator**: Full access, including managing system users.
    - **Librarian**: Manage books, authors, categories, publishers, and borrowing transactions.
    - **Staff**: Manage borrowing transactions and members.
  - Role-based access control for endpoints.

- **Borrowing & Returning**
  - Borrow a book if available.
  - Prevent borrowing if the book is already checked out.
  - Return books and track transaction history.

- **Authentication & Authorization**
  - Secure login with Basic Authentication.
  - Passwords stored securely using BCrypt hashing.
  - Role-based restrictions enforced at the API level.

- **User Activity Logging**
  - All CRUD and transaction operations are automatically logged.
  - Custom actions (e.g., borrow/return) are annotated for precise tracking.
  - Logs stored with user, action, and timestamp.

- **Developer-Friendly**
  - RESTful APIs with consistent structure.
  - Swagger UI and Postman collection for easy testing.
  - Sample SQL scripts and ERD provided for quick setup.
 
---

## 🛠 Tech Stack

- **Backend Framework:** Java 21, Spring Boot
- **Security:** Spring Security with Basic Authentication
- **Database:** MySQL (JPA/Hibernate for ORM)
- **Build Tool:** Maven
- **Documentation & Testing:** Swagger UI, Postman Collection
- **Other:** Lombok (boilerplate reduction), AOP (activity logging)

---

## 🗄 Database Schema & ERD

The system follows a normalized relational database schema to ensure data consistency and scalability.

### 📌 Main Entities
- **Books**
  - id, title, isbn, edition, publication_year, summary, cover_image, language_id, publisher_id
- **Authors**
  - id, name, biography
- **Categories**
  - id, name, parent_id (supports hierarchical classification)
- **Publishers**
  - id, name, address
- **Members**
  - id, name, email, phone, address
- **SystemUsers**
  - id, username, password, role (ADMIN / LIBRARIAN / STAFF)
- **BorrowingTransactions**
  - id, book_id, member_id, processed_by (SystemUser), borrowed_at, returned_at, due_date
- **Book_Author (junction table)**
  - book_id, author_id

### 📌 ERD (Entity Relationship Diagram)

<img width="1097" height="525" alt="Library" src="https://github.com/user-attachments/assets/76b513dd-a859-4e1e-973d-47775f0ecf1f" />

---

## 📖 API Documentation

The system exposes a RESTful API with role-based access control.

---

### 🔐 Authentication
- **Type:** Basic Auth (username + password from `SystemUsers`)
- **Roles:** `ADMIN`, `LIBRARIAN`, `STAFF`

---

### 👤 System Users (Admins, Librarians, Staff)

| Method | Endpoint              | Description         | Role Required |
|--------|-----------------------|---------------------|---------------|
| POST   | /api/users/admin      | Create new Admin    | ADMIN         |
| POST   | /api/users/librarian  | Create new Librarian| ADMIN         |
| POST   | /api/users/staff      | Create new Staff    | ADMIN         |
| GET    | /api/users            | Get all users       | ADMIN         |
| GET    | /api/users/{id}       | Get user by ID      | ADMIN         |
| PUT    | /api/users/{id}       | Update user         | ADMIN         |
| DELETE | /api/users/{id}       | Delete user         | ADMIN         |

---

### 📚 Books

| Method | Endpoint        | Description       | Role Required     |
|--------|-----------------|-------------------|------------------|
| POST   | /api/books      | Add new book      | LIBRARIAN, ADMIN |
| GET    | /api/books      | List all books    | LIBRARIAN, ADMIN |
| GET    | /api/books/{id} | Get book by ID    | LIBRARIAN, ADMIN |
| PUT    | /api/books/{id} | Update book       | LIBRARIAN, ADMIN |
| DELETE | /api/books/{id} | Delete book       | LIBRARIAN, ADMIN |

---

### 🧑‍🤝‍🧑 Members

| Method | Endpoint           | Description       | Role Required                |
|--------|--------------------|-------------------|------------------------------|
| POST   | /api/members       | Add new member    | STAFF, LIBRARIAN, ADMIN      |
| GET    | /api/members       | List all members  | STAFF, LIBRARIAN, ADMIN      |
| GET    | /api/members/{id}  | Get member by ID  | STAFF, LIBRARIAN, ADMIN      |
| PUT    | /api/members/{id}  | Update member     | STAFF, LIBRARIAN, ADMIN      |
| DELETE | /api/members/{id}  | Delete member     | STAFF, LIBRARIAN, ADMIN      |

---

### 🔄 Borrowing Transactions

| Method | Endpoint                       | Description              | Role Required           |
|--------|--------------------------------|--------------------------|-------------------------|
| POST   | /api/transactions/borrow       | Borrow a book            | STAFF, LIBRARIAN, ADMIN |
| PUT    | /api/transactions/return/{id}  | Return a borrowed book   | STAFF, LIBRARIAN, ADMIN |
| GET    | /api/transactions              | List all transactions    | LIBRARIAN, ADMIN        |
| GET    | /api/transactions/{id}         | Get transaction by ID    | LIBRARIAN, ADMIN        |

---

📌 **Note:** A full Postman collection is provided in  
`/docs/LibraryAPI.postman_collection.json`.

---

## 🔐 Authentication & Authorization

- **Authentication**: Implemented using **Spring Security** with **Basic Auth**.  
  Users log in with a username and password stored securely in the database (hashed with BCrypt).  

- **Authorization**: Role-Based Access Control (RBAC) with three main roles:
  - **ADMIN** → Full access (manage users, books, members, transactions).  
  - **LIBRARIAN** → Manage books, borrowing/return transactions, and members.  
  - **STAFF** → Limited access (manage members and borrowing/return operations).  

- Endpoints are protected using `@PreAuthorize` and `HttpSecurity` rules in `SecurityConfig`.  
- Example:
  ```java
  .requestMatchers("/api/books/**").hasAnyAuthority("ADMIN", "LIBRARIAN")
  .requestMatchers("/api/members/**").hasAnyAuthority("ADMIN", "LIBRARIAN", "STAFF")

---
## 📝 Activity Logging

- All **critical actions** (`create`, `update`, `delete`, `borrow`, `return`) are automatically logged using a custom **Spring AOP Aspect**.  
- A custom annotation `@LogActivity` is also supported for marking extra methods that should be logged.  

Each log entry records:  
- **Username** of the acting user  
- **Action** performed (e.g., `CREATE_BOOK`, `DELETE_MEMBER`)  
- **Target class & method**  
- **Timestamp** 

Logs are stored via the `UserActivityLogService` and can be extended for persistence (e.g., database, file storage, or monitoring tools).
---

## 🚀 Future Improvements

- **UI/Frontend Integration**: Add a React or Angular frontend for easier interaction with the system.  
- **Advanced Search & Filtering**: Implement search by author, category, language, or publication year.  
- **Notifications**: Email/SMS reminders for due dates and overdue books.  
- **Reporting & Analytics**: Generate reports on borrowing trends, active members, and popular books.  
- **JWT Authentication**: Replace Basic Auth with JWT tokens for enhanced security.  
- **CI/CD Pipeline**: Automate build, test, and deployment using GitHub Actions or Jenkins.  
- **Dockerization**: Provide Docker images for easier setup and deployment.  
- **Cloud Deployment**: Deploy to AWS/Azure/GCP for scalability.  
