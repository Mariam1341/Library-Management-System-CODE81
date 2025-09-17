package com.code81.library.mapper;

import com.code81.library.dto.BookDTO;
import com.code81.library.entity.Book;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toDTO(Book book);

    Book toEntity(BookDTO bookDTO);

    List<BookDTO> toDTOs(List<Book> books);

    List<Book> toEntities(List<BookDTO> bookDTOs);
}
