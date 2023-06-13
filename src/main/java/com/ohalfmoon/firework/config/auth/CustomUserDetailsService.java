package com.ohalfmoon.firework.config.auth;

import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * packageName :  com.ohalfmoon.firework.config.auth
 * fileName : CustomUserDetailsService
 * author :  ycy
 * date : 2023-06-13
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-13                ycy             최초 생성
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final HttpSession session;
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity entity = memberRepository.findByUsername(username);

        return CustomUserDetails.builder()
                .userNo(entity.getUserNo())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .phoneNum(entity.getPhoneNum())
                .name(entity.getName())
                .manager(entity.isManager())
                .birthdate(entity.getBirthdate())
                .startdate(entity.getStartdate())
                .deptEntity(entity.getDeptEntity())
                .positionEntity(entity.getPositionEntity())
                .roleEntity(entity.getRoleEntity())
                .build();
    }

}
