package com.ohalfmoon.firework.config.auth;

import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.PositionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
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
@NoArgsConstructor
@ToString
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private String name;
    private String email;
    private Long userNo;
    private String phoneNum;
    private DeptEntity deptEntity;
    private PositionEntity positionEntity;
    private boolean manager;
    private Date birthdate;
    private Date Startdate;


    public CustomUserDetails(MemberEntity user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
        this.userNo = user.getUserNo();
        this.phoneNum = user.getPhoneNum();
        this.deptEntity = user.getDeptEntity();
        this.positionEntity = user.getPositionEntity();
        this.manager = user.isManager();
        this.birthdate = user.getBirthdate();
        this.Startdate = user.getStartdate();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
