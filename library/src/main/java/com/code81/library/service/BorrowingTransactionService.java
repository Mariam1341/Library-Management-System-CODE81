package com.code81.library.service;

import com.code81.library.dto.BorrowingTransactionDTO;

import java.util.List;

public interface BorrowingTransactionService {
    BorrowingTransactionDTO createTransaction(BorrowingTransactionDTO transactionDTO);
    BorrowingTransactionDTO updateTransaction(Long id, BorrowingTransactionDTO transactionDTO);
    BorrowingTransactionDTO borrowBook(BorrowingTransactionDTO dto);
    BorrowingTransactionDTO returnBook(Long transactionId);
    void deleteTransaction(Long id);
    BorrowingTransactionDTO getTransactionById(Long id);
    List<BorrowingTransactionDTO> getAllTransactions();
}
