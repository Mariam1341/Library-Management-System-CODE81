package com.code81.library.service;

import com.code81.library.dto.PublisherDTO;

import java.util.List;

public interface PublisherService {
    PublisherDTO createPublisher(PublisherDTO publisherDTO);
    PublisherDTO updatePublisher(Long id, PublisherDTO publisherDTO);
    void deletePublisher(Long id);
    PublisherDTO getPublisherById(Long id);
    List<PublisherDTO> getAllPublishers();
}
