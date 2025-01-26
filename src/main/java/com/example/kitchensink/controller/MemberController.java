package com.example.kitchensink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import com.example.kitchensink.repository.MemberRepository;
import com.example.kitchensink.entity.Member;

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
    public Member registerMember(@RequestBody Member member) {
        return repository.save(member);
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
}
