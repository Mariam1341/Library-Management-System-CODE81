package com.code81.library.controller;

import com.code81.library.dto.ApiResponse;
import com.code81.library.dto.BookDTO;
import com.code81.library.service.BookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;



    @PostMapping
    public ResponseEntity<ApiResponse<BookDTO>> createBook(@RequestBody BookDTO bookDTO) {
        BookDTO created = bookService.createBook(bookDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Book created successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookDTO>> updateBook(
            @PathVariable Long id,
            @RequestBody BookDTO bookDTO) {
        BookDTO updated = bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Book updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Book deleted successfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookDTO>> getBookById(@PathVariable Long id) {
        BookDTO book = bookService.getBookById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Book retrieved successfully", book));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookDTO>>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(new ApiResponse<>(true, "Books retrieved successfully", books));
    }
}
