package com.code81.library.mapper;

import com.code81.library.dto.MemberDTO;
import com.code81.library.entity.Member;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberDTO toDTO(Member member);

    Member toEntity(MemberDTO memberDTO);

    List<MemberDTO> toDTOs(List<Member> members);

    List<Member> toEntities(List<MemberDTO> memberDTOs);
}
