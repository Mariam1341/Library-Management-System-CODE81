package com.code81.library.mapper.helper;

import com.code81.library.entity.Book;
import com.code81.library.repository.BookRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class BookMapperHelper {
    private final BookRepository bookRepository;

    public BookMapperHelper(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Named("mapBook")
    public Book mapBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
}
