package com.code81.library.mapper.helper;

import com.code81.library.entity.SystemUser;
import com.code81.library.repository.SystemUserRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class SystemUserMapperHelper {
    private final SystemUserRepository systemUserRepository;

    public SystemUserMapperHelper(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    @Named("mapUser")
    public SystemUser mapUser(Long id) {
        return systemUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
