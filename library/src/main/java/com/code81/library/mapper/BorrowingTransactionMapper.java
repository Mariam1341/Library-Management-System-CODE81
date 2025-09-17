package com.code81.library.mapper;

import com.code81.library.dto.BorrowingTransactionDTO;
import com.code81.library.entity.BorrowingTransaction;
import com.code81.library.mapper.helper.BookMapperHelper;
import com.code81.library.mapper.helper.MemberMapperHelper;
import com.code81.library.mapper.helper.SystemUserMapperHelper;
import org.mapstruct.*;
import java.util.List;

import org.mapstruct.*;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {BookMapperHelper.class, MemberMapperHelper.class, SystemUserMapperHelper.class}
)
public interface BorrowingTransactionMapper {

    @Mapping(source = "bookId", target = "book", qualifiedByName = "mapBook")
    @Mapping(source = "memberId", target = "member", qualifiedByName = "mapMember")
    @Mapping(source = "processedBy", target = "processedBy", qualifiedByName = "mapUser")
    BorrowingTransaction toEntity(BorrowingTransactionDTO dto);

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "member.id", target = "memberId")
    @Mapping(source = "processedBy.id", target = "processedBy")
    BorrowingTransactionDTO toDTO(BorrowingTransaction entity);

    List<BorrowingTransactionDTO> toDTOs(List<BorrowingTransaction> transactions);
}

