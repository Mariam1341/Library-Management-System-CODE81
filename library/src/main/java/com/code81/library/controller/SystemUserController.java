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

    // ====== Create Librarian ======
    @PostMapping("/librarians")
    public ResponseEntity<ApiResponse<SystemUserDTO>> createLibrarian(@RequestBody SystemUserDTO userDTO) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Librarian created successfully",
                        userService.createUser(userDTO, Role.LIBRARIAN))
        );
    }

    // ====== Create Staff ======
    @PostMapping("/staff")
    public ResponseEntity<ApiResponse<SystemUserDTO>> createStaff(@RequestBody SystemUserDTO userDTO) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Staff created successfully",
                        userService.createUser(userDTO, Role.STAFF))
        );
    }

    // ====== Create Admin ======
    @PostMapping("/admins")
    public ResponseEntity<ApiResponse<SystemUserDTO>> createAdmin(@RequestBody SystemUserDTO userDTO) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Admin created successfully",
                        userService.createUser(userDTO, Role.ADMIN))
        );
    }
    // ====== Update User ======
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SystemUserDTO>> updateUser(
            @PathVariable Long id,
            @RequestBody SystemUserDTO userDTO) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "User updated successfully",
                        userService.updateUser(id, userDTO))
        );
    }
    // ====== Delete User ======
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User deleted successfully", null));
    }

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