package wony.dev.board.member.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import wony.dev.board.member.model.Member;
import wony.dev.board.member.model.MemberDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("Member 전부 찾기")
    void findAll(){
        // given
        // when
        List<Member> members = memberRepository.findAll();

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
        Member saveMember = memberRepository.save(member);

        // when
        Member findMember = memberRepository.findById(saveMember.getId()).get();

        // then
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }

}