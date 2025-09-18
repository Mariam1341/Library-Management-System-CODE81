package com.code81.library.service.impl;

import com.code81.library.dto.AuthorDTO;
import com.code81.library.entity.Author;
import com.code81.library.exception.ResourceNotFoundException;
import com.code81.library.mapper.AuthorMapper;
import com.code81.library.repository.AuthorRepository;
import com.code81.library.service.AuthorService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.toEntity(authorDTO);
        return authorMapper.toDTO(authorRepository.save(author));
    }

    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Author existing = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));

        Author updated = authorMapper.toEntity(authorDTO);
        updated.setId(existing.getId());

        return authorMapper.toDTO(authorRepository.save(updated));
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        return authorMapper.toDTO(
                authorRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id))
        );
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            throw new ResourceNotFoundException("No authors available");
        }
        return authorMapper.toDTOs(authors);
    }
}
