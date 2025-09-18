package com.code81.library.service.impl;

import com.code81.library.dto.BorrowingTransactionDTO;
import com.code81.library.entity.Book;
import com.code81.library.entity.BorrowingTransaction;
import com.code81.library.entity.Member;
import com.code81.library.entity.SystemUser;
import com.code81.library.mapper.BorrowingTransactionMapper;
import com.code81.library.repository.BookRepository;
import com.code81.library.repository.BorrowingTransactionRepository;
import com.code81.library.repository.MemberRepository;
import com.code81.library.repository.SystemUserRepository;
import com.code81.library.service.BorrowingTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowingTransactionServiceImpl implements BorrowingTransactionService {

    private final BorrowingTransactionRepository transactionRepository;
    private final BorrowingTransactionMapper transactionMapper;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final SystemUserRepository userRepository;

    @Override
    public BorrowingTransactionDTO createTransaction(BorrowingTransactionDTO transactionDTO) {
        BorrowingTransaction tx = transactionMapper.toEntity(transactionDTO);
        return transactionMapper.toDTO(transactionRepository.save(tx));
    }

    @Override
    public BorrowingTransactionDTO updateTransaction(Long id, BorrowingTransactionDTO transactionDTO) {
        BorrowingTransaction existing = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        BorrowingTransaction updated = transactionMapper.toEntity(transactionDTO);
        updated.setId(existing.getId());

        return transactionMapper.toDTO(transactionRepository.save(updated));
    }
    @Override
    public BorrowingTransactionDTO borrowBook(BorrowingTransactionDTO dto) {
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        boolean isBorrowed = transactionRepository.existsByBookIdAndReturnedAtIsNull(book.getId());
        if (isBorrowed) {
            throw new IllegalStateException("Book is already borrowed");
        }

       Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        SystemUser user = userRepository.findById(dto.getProcessedBy())
                .orElseThrow(() -> new RuntimeException("System user not found"));

       dto.setBorrowedAt(LocalDateTime.now());
       return createTransaction(dto);
    }
    @Override
    public BorrowingTransactionDTO returnBook(Long transactionId) {
        BorrowingTransactionDTO dto = getTransactionById(transactionId);

        if (dto.getReturnedAt() != null) {
            throw new IllegalStateException("Book already returned");
        }
        dto.setReturnedAt(LocalDateTime.now());
        return updateTransaction(transactionId, dto);
    }


    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public BorrowingTransactionDTO getTransactionById(Long id) {
        return transactionMapper.toDTO(
                transactionRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Transaction not found"))
        );
    }

    @Override
    public List<BorrowingTransactionDTO> getAllTransactions() {
        return transactionMapper.toDTOs(transactionRepository.findAll());
    }
}
