use library_db;
-- Authors
INSERT INTO authors (id, name) VALUES 
(1, 'Frank Herbert'),
(2, 'J.R.R. Tolkien'),
(3, 'George Orwell');

-- Publishers
INSERT INTO publishers (id, name) VALUES 
(1, 'Chilton Books'),
(2, 'Allen & Unwin'),
(3, 'Secker & Warburg');

-- Categories (hierarchical: Fiction > Sci-Fi > Space Opera)
INSERT INTO categories (id, name, parent_id) VALUES
(1, 'Fiction', NULL),
(2, 'Science Fiction', 1),
(3, 'Space Opera', 2),
(4, 'Fantasy', 1),
(5, 'Dystopian', 1);

-- Books
INSERT INTO books (id, title, isbn, edition, publication_year, summary, cover_image, language, category_id, publisher_id) VALUES
(1, 'Dune', '9780441172719', 1, 1965, 'Epic science fiction novel', NULL, 'English', 3, 1),
(2, 'The Lord of the Rings', '9780544003415', 1, 1954, 'High fantasy epic novel', NULL, 'English', 4, 2),
(3, '1984', '9780451524935', 1, 1949, 'Dystopian novel about totalitarianism', NULL, 'English', 5, 3);

-- Book-Authors (Many-to-Many)
INSERT INTO book_authors (book_id, author_id) VALUES
(1, 1), -- Dune by Frank Herbert
(2, 2), -- LOTR by Tolkien
(3, 3); -- 1984 by Orwell

-- Members (borrowers)
INSERT INTO members (id, name, email, phone) VALUES
(1, 'Alice Johnson', 'alice@example.com', '0100000001'),
(2, 'Bob Smith', 'bob@example.com', '0100000002');



-- Borrowing Transactions
-- Assume book 1 borrowed by member 1, and book 3 borrowed by member 2
INSERT INTO borrowing_transactions (id, book_id, member_id, borrowed_at, due_date, returned_at) VALUES
(1, 1, 1, NOW(), DATE_ADD(NOW(), INTERVAL 14 DAY), NULL), -- not returned yet
(2, 3, 2, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), NOW()); -- returned already
