package com.example.kitchensink.controller;

import com.example.kitchensink.entity.Member;
import com.example.kitchensink.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.kitchensink.repository.MemberRepository;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    public void setUp() {
        member = new Member();
        member.setId("1");  // String ID for MongoDB
        member.setFirstName("John");
        member.setLastName("Doe");
        member.setEmail("johndoe@example.com");
    }

    @Test
    public void testGetMemberById() throws Exception {
         Optional<Member> foundMember = Optional.of(member);
        when(memberRepository.findById("1")).thenReturn(foundMember);

        mockMvc.perform(get("/members/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"));
    }

    @Test
    public void testSaveMember() throws Exception {
        when(memberService.saveMember(any(Member.class))).thenReturn(member);

        mockMvc.perform(post("/members")
                        .contentType("application/json")
                        .content("{\"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"johndoe@example.com\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteMember() throws Exception {
        Optional<Member> foundMember = Optional.of(member);
        when(memberRepository.findById("1")).thenReturn(foundMember);

        doNothing().when(memberRepository).deleteById("1");

        mockMvc.perform(delete("/members/1"))
                .andExpect(status().isOk());

        verify(memberRepository, times(1)).deleteById("1");
    }
}
