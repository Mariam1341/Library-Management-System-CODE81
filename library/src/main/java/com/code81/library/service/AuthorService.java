package com.code81.library.service;

import com.code81.library.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    AuthorDTO createAuthor(AuthorDTO authorDTO);
    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);
    void deleteAuthor(Long id);
    AuthorDTO getAuthorById(Long id);
    List<AuthorDTO> getAllAuthors();
}
