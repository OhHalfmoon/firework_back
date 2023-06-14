package com.ohalfmoon.firework.config.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.model.PositionEntity;
import com.ohalfmoon.firework.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * packageName :  com.ohalfmoon.firework.config.auth
 * fileName : CustomUserDetails
 * author :  ycy
 * date : 2023-06-13
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-13                ycy             최초 생성
 */
@Getter
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    private Long username;    // pk값
    private String password;    // pw
    private boolean accountNonLocked = true; // 잠금여부
    private boolean accountNonExpired = true; // 계정 만료 없음
    private boolean credentialsNonExpired = true; // 비밀번호 만료 없음
    private boolean enabled = true; // 활성화 여부
    private Collection<? extends GrantedAuthority> authorities; // 권한목록

    private String realId; // id(username)
    private String email; // email
    private String phoneNum; // 연락처
    private String name; // 이름
    private boolean manager; // 관리자 여부
    private Date birthdate; // 생일
    private Date startdate; // 입사일
    private DeptEntity deptEntity; // 부서
    private PositionEntity positionEntity; // 직급
    private RoleEntity roleEntity; // 권한

    public String getUsername() {
        return realId;
    }
    public CustomUserDetails(MemberResponseDTO dto) {
        this.username = dto.getUserNo();
        this.password = dto.getPassword();
        this.realId = dto.getUsername();
        this.email = dto.getEmail();
        this.phoneNum = dto.getPhoneNum();
        this.name = dto.getName();
        this.manager = dto.isManager();
        this.birthdate = dto.getBirthdate();
        this.startdate = dto.getStartdate();
        this.deptEntity = dto.getDeptEntity();
        this.positionEntity = dto.getPositionEntity();
        Collection<GrantedAuthority> roles =
                Arrays.stream(dto.getRoleEntity().getRoleName().split(","))
                        .map(role -> new SimpleGrantedAuthority(getUsername()))
                        .collect(Collectors.toList());
        this.authorities = roles;
    }
}
