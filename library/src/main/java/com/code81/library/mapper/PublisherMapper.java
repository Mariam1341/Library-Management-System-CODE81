package com.code81.library.mapper;

import com.code81.library.dto.PublisherDTO;
import com.code81.library.entity.Publisher;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    PublisherDTO toDTO(Publisher publisher);

    Publisher toEntity(PublisherDTO publisherDTO);

    List<PublisherDTO> toDTOs(List<Publisher> publishers);

    List<Publisher> toEntities(List<PublisherDTO> publisherDTOs);
}
