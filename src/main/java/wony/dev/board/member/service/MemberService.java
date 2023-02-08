package wony.dev.board.member.service;

import wony.dev.board.member.model.Member;

import java.util.List;

public interface MemberService {
    List<Member> findAll();
}
