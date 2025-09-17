package com.code81.library.repository;

import com.code81.library.entity.BorrowingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingTransactionRepository extends JpaRepository<BorrowingTransaction, Long> {
}
