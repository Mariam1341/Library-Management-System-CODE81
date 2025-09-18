package com.code81.library.controller;

import com.code81.library.dto.ApiResponse;
import com.code81.library.dto.BorrowingTransactionDTO;
import com.code81.library.service.BorrowingTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transactions")
public class BorrowingTransactionController {

    private final BorrowingTransactionService transactionService;



    @PostMapping("/borrow")
    public ResponseEntity<ApiResponse<BorrowingTransactionDTO>> borrowBook(@RequestBody BorrowingTransactionDTO dto) {
        BorrowingTransactionDTO borrowed = transactionService.borrowBook(dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Book borrowed successfully", borrowed));
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<ApiResponse<BorrowingTransactionDTO>> returnBook(@PathVariable Long id) {
        BorrowingTransactionDTO returned = transactionService.returnBook(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Book returned successfully", returned));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BorrowingTransactionDTO>> getTransactionById(@PathVariable Long id) {
        BorrowingTransactionDTO transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Transaction retrieved successfully", transaction));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BorrowingTransactionDTO>>> getAllTransactions() {
        List<BorrowingTransactionDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(new ApiResponse<>(true, "Transactions retrieved successfully", transactions));
    }
}
