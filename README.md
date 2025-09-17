# 📚 Library Management System

This project is a **Library Management System** built with **Java + Spring Boot + Relational Database**.  
The goal of the system is to manage books, members, borrowing transactions, and user roles (Admin, Librarian, Staff).

---

## 📌 ERD (Entity Relationship Diagram)

<img width="1097" height="525" alt="Library" src="https://github.com/user-attachments/assets/76b513dd-a859-4e1e-973d-47775f0ecf1f" />

---

## 📌 Design Choices

### 1. **Books**
- Each book contains detailed metadata:
  - `title`, `isbn`, `edition`, `publication_year`, `summary`, `cover_image`, and `language`.
- Each book belongs to **one publisher** (Many-to-One).
- Each book belongs to **one category** (Many-to-One).
- Each book can have **multiple authors** (Many-to-Many).

---

### 2. **Authors**
- Stored separately to allow reusability.  
- A book can have multiple authors, and an author can write multiple books.  
- Implemented through the **junction table `BOOK_AUTHORS`**.

---

### 3. **Publishers**
- Each publisher has `name`, `country`, `founded_year`, and `bio`.  
- A publisher can publish many books, but each book has only one publisher (One-to-Many).

---

### 4. **Categories**
- Supports a **hierarchical structure** using `parent_id`.  
  - Example: `Science → Computer Science → Artificial Intelligence`.  
- Each book belongs to **exactly one category**.  
- This design avoids over-complication while still supporting subcategories.

---

### 5. **Members**
- Represents library members (borrowers).  
- Stores contact info like `name`, `email`, `phone`, `address`.  
- Members can borrow multiple books over time.

---

### 6. **System Users**
- Represents library staff (Admin, Librarian, Staff).  
- Contains `username`, `password` (securely stored), and `role`.  
- Supports **role-based access control** for different API endpoints.

---

### 7. **Borrowing Transactions**
- Tracks the lifecycle of a borrowed book:
  - `borrowed_at`, `due_date`, `returned_at`.  
- Connected to:
  - **Book** (which book was borrowed).
  - **Member** (who borrowed it).
  - **System User** (who processed the borrowing/return).

---

### 8. **Junction Tables**
- **BOOK_AUTHORS**: Manages the Many-to-Many relationship between Books and Authors.  
- Ensures flexibility for books with multiple authors.

---

## 📌 Why These Design Choices?

- **Scalability**: Supports complex scenarios (multiple authors, hierarchical categories).  
- **Clarity**: Simplifies book-to-category and book-to-publisher relations to keep queries straightforward.  
- **Security**: Separation of Members vs. System Users ensures better access control.  
- **Flexibility**: Easily extensible for future requirements (e.g., e-books, reservations, fines).

---
