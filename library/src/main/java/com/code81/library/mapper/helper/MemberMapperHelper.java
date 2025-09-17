package com.code81.library.mapper.helper;

import com.code81.library.entity.Member;
import com.code81.library.repository.MemberRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class MemberMapperHelper {
    private final MemberRepository memberRepository;

    public MemberMapperHelper(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Named("mapMember")
    public Member mapMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }
}
