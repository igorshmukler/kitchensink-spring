package com.example.kitchensink.service;

import com.example.kitchensink.entity.Member;
import com.example.kitchensink.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private Member member;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        member = new Member();
        member.setId("1");  // String for MongoDB
        member.setFirstName("John");
        member.setLastName("Doe");
        member.setEmail("johndoe@example.com");
    }

    @Test
    public void testFindMemberById() {
        when(memberRepository.findById("1")).thenReturn(Optional.of(member));
        Member foundMember = memberService.findMemberById("1");
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getFirstName()).isEqualTo("John");
        assertThat(foundMember.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void testSaveMember() {
        when(memberRepository.save(member)).thenReturn(member);
        Member savedMember = memberService.saveMember(member);
        assertThat(savedMember.getFirstName()).isEqualTo("John");
        assertThat(savedMember.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void testDeleteMember() {
        memberService.deleteMember("1");
        verify(memberRepository, times(1)).deleteById("1");
    }
}
