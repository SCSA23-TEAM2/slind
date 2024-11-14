package com.team2.slind.member.mapper;

import com.team2.slind.member.vo.Member;

import java.util.Optional;

public interface MemberMapper {
    Optional<Member> findByMemberId(String memberId);

    void addMember(Member member);

    Optional<Member> findByNickname(String nickname);
}
