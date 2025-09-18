package com.code81.library.service;

import com.code81.library.dto.SystemUserDTO;
import com.code81.library.enums.Role;

import java.util.List;

public interface SystemUserService {
    SystemUserDTO createUser(SystemUserDTO userDTO, Role role);
    SystemUserDTO updateUser(Long id, SystemUserDTO userDTO);
    void deleteUser(Long id);
    SystemUserDTO getUserById(Long id);
    List<SystemUserDTO> getAllUsers();
    SystemUserDTO findByUsername(String username);
    List<SystemUserDTO> getUsersByRole(Role role);
}
