package com.ohalfmoon.firework.config.auth;

import com.ohalfmoon.firework.dto.member.MemberDTO;
import com.ohalfmoon.firework.dto.member.MemberLoginDTO;
import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.MemberRepository;
import com.ohalfmoon.firework.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;

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

    private MemberRepository memberRepository;

    @Autowired
    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 배포시 주석 해제
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        MemberEntity entity = memberRepository.findByUsername(username);
////                .orElseThrow(() -> new UsernameNotFoundException("사용자가 존재하지 않습니다."));
//        if(entity.getState() == 1) { // 가입 승인된 유저만 로그인 가능
//            return new CustomUserDetails(entity);
//        }
//        return null;
//    }

    // 배포 전 임시 코드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity entity = memberRepository.findByUsername(username);
        log.info("entity :{}", entity);

                //.orElseThrow(() -> new UsernameNotFoundException("사용자가 존재하지 않습니다."));
        log.info("state : {}", entity.getState());
        return new CustomUserDetails(entity);
    }





}
