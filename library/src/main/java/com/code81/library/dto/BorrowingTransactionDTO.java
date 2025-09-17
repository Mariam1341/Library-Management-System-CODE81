package com.code81.library.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowingTransactionDTO {

    private Long id;

    @NotNull(message = "Book is required")
    private Long bookId;

    @NotNull(message = "Member is required")
    private Long memberId;

    @NotNull(message = "ProcessedBy (SystemUser) is required")
    private Long processedBy;

    @NotNull(message = "BorrowedAt is required")
    private LocalDateTime borrowedAt;

    @NotNull(message = "DueDate is required")
    private LocalDateTime dueDate;

    private LocalDateTime returnedAt;
}
