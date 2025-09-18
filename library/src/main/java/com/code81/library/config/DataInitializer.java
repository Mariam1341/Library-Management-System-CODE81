package com.code81.library.config;

import com.code81.library.entity.SystemUser;
import com.code81.library.enums.Role;
import com.code81.library.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initAdmin(SystemUserRepository systemUserRepository) {
        return args -> {
            if (systemUserRepository.findByUsername("admin").isEmpty()) {
                SystemUser admin = new SystemUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("1234"));
                admin.setRole(Role.ADMIN);
                systemUserRepository.save(admin);
                System.out.println("Default admin user created: username=admin, password=1234");
            }
        };
    }
}
