package wony.dev.board.member.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wony.dev.board.member.model.Member;
import wony.dev.board.member.model.MemberDTO;
import wony.dev.board.member.service.MemberService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceImplTest {
    
    @Autowired
    MemberService memberService;

    @BeforeEach
    void setUp(){
        memberService.deleteAll();
    }

    @Test
    @DisplayName("Member 전부 찾기")
    void findAll(){
        // given
        // when
        List<Member> members = memberService.findAll();

        // then
        assertThat(members.size()).isEqualTo(0);
    }
    
    @Test
    @DisplayName("특정 Member 찾기")
    void findById(){
        // given
        MemberDTO memberDTO = MemberDTO.builder()
                .name("test")
                .build();
        Member member = memberDTO.toEntity();
        MemberDTO saveMember = memberService.save(memberDTO);

        // when
        Member findMember = memberService.findById(saveMember.getId());

        // then
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }
    
    @Test
    @DisplayName("Member 저장")
    void save(){
        // given
        MemberDTO memberDTO = MemberDTO.builder()
                .name("test")
                .build();
        // when
        MemberDTO saveMember = memberService.save(memberDTO);

        // then
        assertThat(saveMember.getName()).isEqualTo("test");
    }

    @Test
    @DisplayName("Member 수정")
    void update(){
        // given
        MemberDTO memberDTO = MemberDTO.builder()
                .name("test")
                .build();
        MemberDTO saveMember = memberService.save(memberDTO);
        Long id = saveMember.getId();
        MemberDTO updateMemberDTO = MemberDTO.builder()
                .id(id)
                .name("update")
                .build();

        // when
        memberService.update(id, updateMemberDTO);
        Member findMember = memberService.findById(id);

        // then
        assertThat(findMember.getName()).isEqualTo("update");
    }

    @Test
    @DisplayName("Member 삭제")
    void delete(){
        // given
        MemberDTO memberDTO = MemberDTO.builder()
                .name("test")
                .build();
        MemberDTO saveMember = memberService.save(memberDTO);

        // when
        memberService.delete(saveMember.getId());
        List<Member> members = memberService.findAll();

        // then
        assertThat(members.size()).isEqualTo(0);
    }
}