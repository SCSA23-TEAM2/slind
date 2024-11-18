package com.team2.slind.member.login.service;

import com.team2.slind.member.mapper.MemberMapper;
import com.team2.slind.member.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberMapper memberMapper;
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberMapper.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with memberId: " + memberId));

        return new CustomMemberDetails(member.getMemberPk(), member.getMemberId(), member.getMemberPassword());
    }
}