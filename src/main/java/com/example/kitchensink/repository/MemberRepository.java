package com.example.kitchensink.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.kitchensink.entity.Member;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {
    Member findByEmail(String email);
}
