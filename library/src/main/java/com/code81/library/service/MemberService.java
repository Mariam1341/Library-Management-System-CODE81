package com.code81.library.service;

import com.code81.library.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    MemberDTO createMember(MemberDTO memberDTO);
    MemberDTO updateMember(Long id, MemberDTO memberDTO);
    void deleteMember(Long id);
    MemberDTO getMemberById(Long id);
    List<MemberDTO> getAllMembers();
}
