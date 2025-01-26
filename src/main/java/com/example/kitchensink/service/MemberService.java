package com.example.kitchensink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kitchensink.entity.Member;
import com.example.kitchensink.repository.MemberRepository;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository repository;

    @Transactional
    public void register(Member member) {
        repository.save(member);
    }
}
