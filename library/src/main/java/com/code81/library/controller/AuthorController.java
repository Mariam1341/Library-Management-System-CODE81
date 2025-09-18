package com.code81.library.controller;

import com.code81.library.dto.ApiResponse;
import com.code81.library.dto.AuthorDTO;
import com.code81.library.service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authors")
public class AuthorController {

    private  final AuthorService authorService;


    @PostMapping
    public ResponseEntity<ApiResponse<AuthorDTO>> createAuthor(@RequestBody AuthorDTO authorDTO) {
        AuthorDTO created = authorService.createAuthor(authorDTO);
        return ResponseEntity.ok(new ApiResponse<AuthorDTO>(true, "Author created successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorDTO>> updateAuthor(
            @PathVariable Long id,
            @RequestBody AuthorDTO authorDTO) {
        AuthorDTO updated = authorService.updateAuthor(id, authorDTO);
        return ResponseEntity.ok(new ApiResponse<AuthorDTO>(true, "Author updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok(new ApiResponse<Void>(true, "Author deleted successfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorDTO>> getAuthorById(@PathVariable Long id) {
        AuthorDTO author = authorService.getAuthorById(id);
        return ResponseEntity.ok(new ApiResponse<AuthorDTO>(true, "Author retrieved successfully", author));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AuthorDTO>>> getAllAuthors() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(new ApiResponse<List<AuthorDTO>>(true, "Authors retrieved successfully", authors));
    }
}
