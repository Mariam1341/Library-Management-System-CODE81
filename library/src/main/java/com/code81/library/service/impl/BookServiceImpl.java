package com.code81.library.service.impl;

import com.code81.library.dto.BookDTO;
import com.code81.library.entity.Book;
import com.code81.library.exception.BadRequestException;
import com.code81.library.exception.ResourceNotFoundException;
import com.code81.library.mapper.BookMapper;
import com.code81.library.repository.BookRepository;
import com.code81.library.service.BookService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        if (bookRepository.existsByIsbn(bookDTO.getIsbn())) {
            throw new BadRequestException("ISBN already exists: " + bookDTO.getIsbn());
        }
        Book book = bookMapper.toEntity(bookDTO);
        return bookMapper.toDTO(bookRepository.save(book));
    }


    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        Book updated = bookMapper.toEntity(bookDTO);
        updated.setId(existing.getId());

        return bookMapper.toDTO(bookRepository.save(updated));
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public BookDTO getBookById(Long id) {
        return bookMapper.toDTO(
                bookRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id))
        );
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("No books available");
        }
        return bookMapper.toDTOs(books);
    }
}
