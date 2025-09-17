package com.code81.library.mapper;

import com.code81.library.dto.BorrowingTransactionDTO;
import com.code81.library.entity.BorrowingTransaction;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BorrowingTransactionMapper {

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "member.id", target = "memberId")
    @Mapping(source = "processedBy.id", target = "processedBy")
    BorrowingTransactionDTO toDTO(BorrowingTransaction transaction);

    @Mapping(source = "bookId", target = "book.id")
    @Mapping(source = "memberId", target = "member.id")
    @Mapping(source = "processedBy", target = "processedBy.id")
    BorrowingTransaction toEntity(BorrowingTransactionDTO transactionDTO);

    List<BorrowingTransactionDTO> toDTOs(List<BorrowingTransaction> transactions);

    List<BorrowingTransaction> toEntities(List<BorrowingTransactionDTO> transactionDTOs);
}
