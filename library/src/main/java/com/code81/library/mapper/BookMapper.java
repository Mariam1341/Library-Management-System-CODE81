package com.code81.library.mapper;

import com.code81.library.dto.BookDTO;
import com.code81.library.entity.Book;
import com.code81.library.mapper.helper.CategoryMapperHelper;
import com.code81.library.mapper.helper.PublisherMapperHelper;
import org.mapstruct.*;
import java.util.List;

import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapperHelper.class, PublisherMapperHelper.class})
public interface BookMapper {

    @Mapping(source = "categoryId", target = "category", qualifiedByName = "mapCategory")
    @Mapping(source = "publisherId", target = "publisher", qualifiedByName = "mapPublisher")
    Book toEntity(BookDTO dto);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "publisher.id", target = "publisherId")
    BookDTO toDTO(Book entity);

    List<BookDTO> toDTOs(List<Book> books);
}
