package com.code81.library.repository;

import com.code81.library.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    SystemUser findByUsername(String username);
}
