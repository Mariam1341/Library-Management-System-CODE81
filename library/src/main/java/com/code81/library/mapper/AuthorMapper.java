package com.code81.library.mapper;

import com.code81.library.dto.AuthorDTO;
import com.code81.library.entity.Author;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO toDTO(Author author);

    Author toEntity(AuthorDTO authorDTO);

    List<AuthorDTO> toDTOs(List<Author> authors);

    List<Author> toEntities(List<AuthorDTO> authorDTOs);
}
