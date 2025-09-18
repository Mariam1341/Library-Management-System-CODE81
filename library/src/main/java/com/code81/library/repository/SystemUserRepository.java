package com.code81.library.repository;

import com.code81.library.entity.SystemUser;
import com.code81.library.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    Optional<SystemUser> findByUsername(String username);
    boolean existsByUsername(String username);

    List<SystemUser> findByRole(Role role);

}
