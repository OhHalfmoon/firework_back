package com.ohalfmoon.firework.config.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.model.PositionEntity;
import com.ohalfmoon.firework.model.RoleEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

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
@Builder
public class CustomUserDetails implements UserDetails {
    private Long userNo;
    private String username;
    private String password;
    private String email;
    private String phoneNum;
    private String name;
    private boolean manager;
    private Date birthdate;
    private Date startdate;
    private DeptEntity deptEntity;
    private PositionEntity positionEntity;
    private RoleEntity roleEntity;

    public CustomUserDetails(MemberResponseDTO dto) {
        this.userNo = dto.getUserNo();
        this.username = dto.getUsername();
        this.email = dto.getEmail();
        this.phoneNum = dto.getPhoneNum();
        this.name = dto.getName();
        this.manager = dto.isManager();
        this.birthdate = dto.getBirthdate();
        this.startdate = dto.getStartdate();
        this.deptEntity = dto.getDeptEntity();
        this.positionEntity = dto.getPositionEntity();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<>
        return null;
    }


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
