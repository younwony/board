package wony.dev.board.member.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wony.dev.board.member.model.Member;
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
}
