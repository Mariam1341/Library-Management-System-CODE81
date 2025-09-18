package com.code81.library.controller;

import com.code81.library.dto.ApiResponse;
import com.code81.library.dto.PublisherDTO;
import com.code81.library.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping
    public ResponseEntity<ApiResponse<PublisherDTO>> createPublisher(@RequestBody PublisherDTO dto) {
        PublisherDTO created = publisherService.createPublisher(dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Publisher created successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PublisherDTO>> updatePublisher(
            @PathVariable Long id,
            @RequestBody PublisherDTO dto) {
        PublisherDTO updated = publisherService.updatePublisher(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Publisher updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Publisher deleted successfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PublisherDTO>> getPublisherById(@PathVariable Long id) {
        PublisherDTO publisher = publisherService.getPublisherById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Publisher retrieved successfully", publisher));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PublisherDTO>>> getAllPublishers() {
        List<PublisherDTO> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(new ApiResponse<>(true, "Publishers retrieved successfully", publishers));
    }
}
