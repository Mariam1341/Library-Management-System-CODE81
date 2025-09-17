package com.code81.library.mapper.helper;

import com.code81.library.entity.Publisher;
import com.code81.library.repository.PublisherRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapperHelper {
    private final PublisherRepository publisherRepository;

    public PublisherMapperHelper(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Named("mapPublisher")
    public Publisher mapPublisher(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
    }
}
