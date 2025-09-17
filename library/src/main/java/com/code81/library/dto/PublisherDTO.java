package com.code81.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherDTO {

    private Long id;

    @NotBlank(message = "Publisher name is required")
    private String name;

    @NotBlank(message = "Publisher country is required")
    private String country;

    private Integer foundedYear;

    @Size(max = 2000, message = "Bio cannot exceed 2000 characters")
    private String bio;
}
