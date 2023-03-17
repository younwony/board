package wony.dev.board.member.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wony.dev.board.member.model.Member;
import wony.dev.board.member.model.MemberDTO;
import wony.dev.board.member.repository.MemberRepository;
import wony.dev.board.member.service.MemberService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
    }

    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        Member saveMember = memberRepository.save(memberDTO.toEntity());
        return MemberDTO.toDTO(saveMember);
    }

    @Override
    public void update(Long id, MemberDTO memberDTO) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
        member.update(memberDTO);
        memberRepository.save(member);
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        memberRepository.deleteAll();
    }
}
