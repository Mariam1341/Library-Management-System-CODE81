package com.code81.library.service.impl;

import com.code81.library.dto.SystemUserDTO;
import com.code81.library.entity.SystemUser;
import com.code81.library.enums.Role;
import com.code81.library.exception.BadRequestException;
import com.code81.library.exception.ResourceNotFoundException;
import com.code81.library.mapper.SystemUserMapper;
import com.code81.library.repository.SystemUserRepository;
import com.code81.library.service.SystemUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepository systemUserRepository;
    private final SystemUserMapper systemUserMapper;
    private final PasswordEncoder passwordEncoder;



    @Override
    public SystemUserDTO createUser(SystemUserDTO userDTO, Role role) {
        if (systemUserRepository.existsByUsername(userDTO.getUsername())) {
            throw new BadRequestException("Username already exists: " + userDTO.getUsername());
        }

        SystemUser user = systemUserMapper.toEntity(userDTO);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return systemUserMapper.toDTO(systemUserRepository.save(user));
    }


    @Override
    public SystemUserDTO updateUser(Long id, SystemUserDTO userDTO) {
        SystemUser existing = systemUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (userDTO.getUsername() != null && !userDTO.getUsername().isEmpty()) {
            existing.setUsername(userDTO.getUsername());
        }
        if (userDTO.getRole() != null) {
            existing.setRole(Role.valueOf(userDTO.getRole()));
        }
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        return systemUserMapper.toDTO(systemUserRepository.save(existing));
    }

    @Override
    public void deleteUser(Long id) {
        if (!systemUserRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        systemUserRepository.deleteById(id);
    }

    @Override
    public SystemUserDTO getUserById(Long id) {
        return systemUserMapper.toDTO(
                systemUserRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id))
        );
    }

    @Override
    public List<SystemUserDTO> getUsersByRole(Role role) {
        return systemUserMapper.toDTOs(systemUserRepository.findByRole(role));
    }

    @Override
    public List<SystemUserDTO> getAllUsers() {
        List<SystemUser> users = systemUserRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users available");
        }
        return systemUserMapper.toDTOs(users);
    }

    @Override
    public SystemUserDTO findByUsername(String username) {
        return systemUserMapper.toDTO(
                systemUserRepository.findByUsername(username)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username))
        );
    }

}
