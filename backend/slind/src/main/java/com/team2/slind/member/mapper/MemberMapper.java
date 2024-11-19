package com.team2.slind.member.mapper;

import com.team2.slind.judgement.vo.Judgement;
import com.team2.slind.member.vo.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface MemberMapper {
    Optional<Member> findByMemberId(String memberId);

    void addMember(Member member);

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByMemberPk(Long memberPk);

    void updateMemberInfo(@Param("nickname") String nickname,
                          @Param("questionPk") Long questionPk,
                          @Param("answer") String answer,
                          @Param("memberPk") Long memberPk);
    int findCountByPk(@Param("memberPk") Long memberPk);

    void deleteByMemberPk(Long memberPk);
}
