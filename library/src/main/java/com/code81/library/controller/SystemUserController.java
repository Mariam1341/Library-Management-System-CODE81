package com.code81.library.controller;

import com.code81.library.dto.ApiResponse;
import com.code81.library.dto.SystemUserDTO;
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

    @PostMapping
    public ResponseEntity<ApiResponse<SystemUserDTO>> createUser(@RequestBody SystemUserDTO dto) {
        SystemUserDTO created = userService.createUser(dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "User created successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SystemUserDTO>> updateUser(
            @PathVariable Long id,
            @RequestBody SystemUserDTO dto) {
        SystemUserDTO updated = userService.updateUser(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "User updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User deleted successfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SystemUserDTO>> getUserById(@PathVariable Long id) {
        SystemUserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User retrieved successfully", user));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SystemUserDTO>>> getAllUsers() {
        List<SystemUserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>(true, "Users retrieved successfully", users));
    }
}

