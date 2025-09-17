package com.code81.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO {

    private Long id;

    @NotBlank(message = "Author name is required")
    @Size(max = 255, message = "Author name cannot exceed 255 characters")
    private String name;

    @Size(max = 2000, message = "Bio cannot exceed 2000 characters")
    private String bio;
}
