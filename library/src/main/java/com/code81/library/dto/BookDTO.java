package com.code81.library.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    private Integer edition;

    private Integer publicationYear;

    @Size(max = 2000, message = "Summary cannot exceed 2000 characters")
    private String summary;

    private String coverImage;

    @NotBlank(message = "Language is required")
    private String language;

    @NotNull(message = "Category is required")
    private Long categoryId;

    @NotNull(message = "Publisher is required")
    private Long publisherId;
}
