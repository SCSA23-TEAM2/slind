package com.team2.slind.security.util;

import com.team2.slind.member.login.service.CustomMemberDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    // accessToken 가져오기
    public static String getAccessToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("인증된 사용자가 아닙니다.");
        }
        return (String) authentication.getCredentials();
    }

    // memberPk 가져오기
    public static Long getMemberPk() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomMemberDetails)) {
            throw new IllegalStateException("인증된 사용자가 아닙니다.");
        }
        CustomMemberDetails memberDetails = (CustomMemberDetails) authentication.getPrincipal();
        return memberDetails.getMemberPk();
    }

    // memberId 가져오기
    public static String getMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("인증된 사용자가 아닙니다.");
        }
        return authentication.getName();
    }
}
