package com.example.kitchensink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import com.example.kitchensink.repository.MemberRepository;
import com.example.kitchensink.entity.Member;

import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberRepository repository;

    @GetMapping
    public List<Member> listAllMembers() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> registerMember(@RequestBody Member member) {
        if (validateMember(member)) {
            Member savedMember = repository.save(member);
            return new ResponseEntity<>("Member added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid member data", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable String id, @RequestBody Member member) {
        repository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        return repository.save(member);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMemberById(@PathVariable String id) {
        Optional<Member> member = repository.findById(id);

        if (member.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>("Member deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Member not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
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
