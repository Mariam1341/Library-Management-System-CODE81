package com.code81.library.controller;

import com.code81.library.dto.ApiResponse;
import com.code81.library.dto.SystemUserDTO;
import com.code81.library.enums.Role;
import com.code81.library.service.SystemUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class SystemUserController {

    private final SystemUserService userService;

    // ====== Get All Librarians ======
    @GetMapping("/librarians")
    public ResponseEntity<ApiResponse<List<SystemUserDTO>>> getAllLibrarians() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "List of librarians",
                        userService.getUsersByRole(Role.LIBRARIAN))
        );
    }

    // ====== Get All Staff ======
    @GetMapping("/staff")
    public ResponseEntity<ApiResponse<List<SystemUserDTO>>> getAllStaff() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "List of staff",
                        userService.getUsersByRole(Role.STAFF))
        );
    }

    // ====== Get All Users ======
    @GetMapping
    public ResponseEntity<ApiResponse<List<SystemUserDTO>>> getAllUsers() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "List of all users", userService.getAllUsers())
        );
    }

    // ====== Get User by ID ======
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SystemUserDTO>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "User found", userService.getUserById(id))
        );
    }




}