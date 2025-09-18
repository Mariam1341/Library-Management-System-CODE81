package com.code81.library.controller;

import com.code81.library.dto.ApiResponse;
import com.code81.library.dto.MemberDTO;
import com.code81.library.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse<MemberDTO>> createMember(@RequestBody MemberDTO dto) {
        MemberDTO created = memberService.createMember(dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Member created successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberDTO>> updateMember(
            @PathVariable Long id,
            @RequestBody MemberDTO dto) {
        MemberDTO updated = memberService.updateMember(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Member updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Member deleted successfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberDTO>> getMemberById(@PathVariable Long id) {
        MemberDTO member = memberService.getMemberById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Member retrieved successfully", member));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MemberDTO>>> getAllMembers() {
        List<MemberDTO> members = memberService.getAllMembers();
        return ResponseEntity.ok(new ApiResponse<>(true, "Members retrieved successfully", members));
    }
}
