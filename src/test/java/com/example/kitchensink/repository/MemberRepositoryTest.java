package com.example.kitchensink.repository;

import com.example.kitchensink.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    public void setUp() {
        memberRepository.deleteAll();  // Reset DB between tests
        member = new Member();
        member.setId("1");  // Assume id is a String for MongoDB
        member.setFirstName("John");
        member.setLastName("Doe");
        member.setEmail("johndoe@example.com");
    }

    @Test
    public void testSaveMember() {
        memberRepository.save(member);
        assertThat(memberRepository.findById("1").isPresent()).isTrue();
    }

    @Test
    public void testFindByEmail() {
        memberRepository.save(member);
        Member foundMember = memberRepository.findByEmail("johndoe@example.com");
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getEmail()).isEqualTo("johndoe@example.com");
    }

    @Test
    public void testDeleteMember() {
        memberRepository.save(member);
        memberRepository.deleteById("1");
        assertThat(memberRepository.findById("1").isEmpty()).isTrue();
    }
}
