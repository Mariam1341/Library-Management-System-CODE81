use library_db;
-- ===== CATEGORIES =====
INSERT INTO categories (id, name, parent_id) VALUES (1, 'Fiction', NULL);
INSERT INTO categories (id, name, parent_id) VALUES (2, 'Science Fiction', 1);
INSERT INTO categories (id, name, parent_id) VALUES (3, 'Space Opera', 2);
INSERT INTO categories (id, name, parent_id) VALUES (4, 'Non-Fiction', NULL);
INSERT INTO categories (id, name, parent_id) VALUES (5, 'History', 4);

-- ===== PUBLISHERS =====
INSERT INTO publishers (id, name) VALUES (1, 'Penguin Random House');
INSERT INTO publishers (id, name) VALUES (2, 'HarperCollins');

-- ===== AUTHORS =====
INSERT INTO authors (id, name) VALUES (1, 'Frank Herbert');
INSERT INTO authors (id, name) VALUES (2, 'Brian Herbert');
INSERT INTO authors (id, name) VALUES (3, 'Yuval Noah Harari');

-- ===== BOOKS =====
INSERT INTO books (id, title, isbn, edition, publication_year, summary, cover_image, language, category_id, publisher_id)
VALUES (1, 'Dune', '9780441172719', 1, 1965, 'Classic sci-fi novel.', 'dune.jpg', 'English', 3, 1);

INSERT INTO books (id, title, isbn, edition, publication_year, summary, cover_image, language, category_id, publisher_id)
VALUES (2, 'Sapiens: A Brief History of Humankind', '9780099590088', 1, 2011, 'Exploration of human history.', 'sapiens.jpg', 'English', 5, 2);

-- ===== BOOK_AUTHORS (many-to-many) =====
INSERT INTO book_authors (book_id, author_id) VALUES (1, 1);
INSERT INTO book_authors (book_id, author_id) VALUES (1, 2);
INSERT INTO book_authors (book_id, author_id) VALUES (2, 3);


-- ===== MEMBERS =====
INSERT INTO members (id, name, email, phone) 
VALUES (1, 'Alice Johnson', 'alice@example.com', '01012345678');

INSERT INTO members (id, name, email, phone) 
VALUES (2, 'Bob Smith', 'bob@example.com', '01087654321');

-- ===== SYSTEM USERS =====
-- Admin
INSERT INTO system_users (id, username, password, role) 
VALUES (1, 'admin', '$2a$10$BpQ9uEo8KnZ1L.Y2X1rJJea7jGhzB0P4YIfQ5eC5lH/n2m7pQeh7K', 'ADMIN'); 
-- (password = 1234)

-- Librarian
INSERT INTO system_users (id, username, password, role) 
VALUES (2, 'librarian01', '$2a$10$BpQ9uEo8KnZ1L.Y2X1rJJea7jGhzB0P4YIfQ5eC5lH/n2m7pQeh7K', 'LIBRARIAN'); 

-- Staff
INSERT INTO system_users (id, username, password, role) 
VALUES (3, 'staff01', '$2a$10$BpQ9uEo8KnZ1L.Y2X1rJJea7jGhzB0P4YIfQ5eC5lH/n2m7pQeh7K', 'STAFF'); 

-- ===== TRANSACTIONS =====
-- Alice (member 1) borrows Dune (book 1) processed by staff01 (user 3)
INSERT INTO transactions (id, book_id, member_id, processed_by, borrowed_at, due_date, returned_at)
VALUES (1, 1, 1, 3, '2025-09-01 10:00:00', '2025-09-15 10:00:00', NULL);

-- Bob (member 2) borrowed Sapiens (book 2) and returned it
INSERT INTO transactions (id, book_id, member_id, processed_by, borrowed_at, due_date, returned_at)
VALUES (2, 2, 2, 2, '2025-08-10 14:00:00', '2025-08-24 14:00:00', '2025-08-20 09:00:00');
