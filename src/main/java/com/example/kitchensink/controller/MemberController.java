package com.example.kitchensink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

import com.example.kitchensink.repository.MemberRepository;
import com.example.kitchensink.service.MemberService;
import com.example.kitchensink.entity.Member;

import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private MemberService memberService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Member> listAllMembers() {
        return memberService.findAllMembers();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<String> registerMember(@RequestBody Member member) {
        if (validateMember(member)) {
            memberService.saveMember(member);
            return new ResponseEntity<>("Member added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid member data", HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public Member updateMember(@PathVariable String id, @RequestBody Member member) {
        Member existingMember =  memberService.findMemberById(id);
        if (existingMember == null) {
            throw new RuntimeException("Member not found");
        }

        return memberService.saveMember(member);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMemberById(@PathVariable String id) {
        Member member = memberService.findMemberById(id);

        if (member != null) {
            memberService.deleteMember(id);
            return new ResponseEntity<>("Member deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Member not found", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable String id) {
        Member member =  memberService.findMemberById(id);
        if (member == null) {
            throw new RuntimeException("Member not found");
        }

        return member;
    }

    private boolean emailAlreadyExists(String email) {
        Member member = repository.findByEmail(email);

        return member != null;
    }

    private boolean validateMember(Member member) {
        if (emailAlreadyExists(member.getEmail())) {
            return false;
        }

        return true; // Valid if the checks pass
    }
}
