package wony.dev.board.member.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wony.dev.board.member.model.Member;
import wony.dev.board.member.service.MemberService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceImplTest {
    
    @Autowired
    MemberService memberService;
    
    @Test
    @DisplayName("Member 전부 찾기")
    void findAll(){
        // given
        // when
        List<Member> members = memberService.findAll();

        // then
        assertThat(members.size()).isEqualTo(0);
    }
}