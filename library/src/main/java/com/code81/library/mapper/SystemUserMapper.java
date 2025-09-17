package com.code81.library.mapper;

import com.code81.library.dto.SystemUserDTO;
import com.code81.library.entity.SystemUser;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SystemUserMapper {

    @Mapping(target = "password", ignore = true)
    SystemUserDTO toDTO(SystemUser user);

    SystemUser toEntity(SystemUserDTO userDTO);

    List<SystemUserDTO> toDTOs(List<SystemUser> users);

    List<SystemUser> toEntities(List<SystemUserDTO> userDTOs);
}
