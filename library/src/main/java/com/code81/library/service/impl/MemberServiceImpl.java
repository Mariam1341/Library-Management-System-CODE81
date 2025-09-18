package com.code81.library.service.impl;

import com.code81.library.dto.MemberDTO;
import com.code81.library.entity.Member;
import com.code81.library.exception.BadRequestException;
import com.code81.library.exception.ResourceNotFoundException;
import com.code81.library.mapper.MemberMapper;
import com.code81.library.repository.MemberRepository;
import com.code81.library.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;


    @Override
    public MemberDTO createMember(MemberDTO memberDTO) {
        if (memberRepository.existsByEmail(memberDTO.getEmail())) {
            throw new BadRequestException("Email already exists: " + memberDTO.getEmail());
        }
        Member member = memberMapper.toEntity(memberDTO);
        return memberMapper.toDTO(memberRepository.save(member));
    }


    @Override
    public MemberDTO updateMember(Long id, MemberDTO memberDTO) {
        Member existing = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));

        Member updated = memberMapper.toEntity(memberDTO);
        updated.setId(existing.getId());

        return memberMapper.toDTO(memberRepository.save(updated));
    }

    @Override
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }

    @Override
    public MemberDTO getMemberById(Long id) {
        return memberMapper.toDTO(
                memberRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id))
        );
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        if (members.isEmpty()) {
            throw new ResourceNotFoundException("No members available");
        }
        return memberMapper.toDTOs(members);
    }
}
