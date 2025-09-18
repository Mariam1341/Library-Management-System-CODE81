package com.code81.library.service.impl;

import com.code81.library.dto.PublisherDTO;
import com.code81.library.entity.Publisher;
import com.code81.library.exception.ResourceNotFoundException;
import com.code81.library.mapper.PublisherMapper;
import com.code81.library.repository.PublisherRepository;
import com.code81.library.service.PublisherService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherServiceImpl(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    @Override
    public PublisherDTO createPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        return publisherMapper.toDTO(publisherRepository.save(publisher));
    }

    @Override
    public PublisherDTO updatePublisher(Long id, PublisherDTO publisherDTO) {
        Publisher existing = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + id));

        Publisher updated = publisherMapper.toEntity(publisherDTO);
        updated.setId(existing.getId());

        return publisherMapper.toDTO(publisherRepository.save(updated));
    }

    @Override
    public void deletePublisher(Long id) {
        if (!publisherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Publisher not found with id: " + id);
        }
        publisherRepository.deleteById(id);
    }

    @Override
    public PublisherDTO getPublisherById(Long id) {
        return publisherMapper.toDTO(
                publisherRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + id))
        );
    }

    @Override
    public List<PublisherDTO> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        if (publishers.isEmpty()) {
            throw new ResourceNotFoundException("No publishers available");
        }
        return publisherMapper.toDTOs(publishers);
    }
}
