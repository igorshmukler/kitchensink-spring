package com.example.kitchensink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
    }
}
