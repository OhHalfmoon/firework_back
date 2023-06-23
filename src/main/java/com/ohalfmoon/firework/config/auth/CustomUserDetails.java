package com.ohalfmoon.firework.config.auth;

import com.ohalfmoon.firework.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.*;
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
@ToString
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private String name;
    private String email;
    private Long userNo;
    private String phoneNum;
    private DeptEntity deptEntity;
    private String deptName;
    private PositionEntity positionEntity;
    private String positionName;
    private boolean manager;
    private LocalDate birthdate;
    private LocalDate Startdate;
    private Role roleName;


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
        this.roleName = user.getRoleName();
        this.positionName = user.getPositionEntity().getPositionName();
        this.deptName = user.getDeptEntity().getDeptName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<String> list = new ArrayList<>();
//        list.add()
//        RoleEntity roles = new RoleEntity();
//        return Arrays.asList(new SimpleGrantedAuthority(roles.getRoleName().getKey()));
//        return Arrays.asList(new SimpleGrantedAuthority(new RoleEntity().getRoleName().toString()));
//        return Arrays.asList(new SimpleGrantedAuthority(list.toString()));
        return Collections.singletonList(new SimpleGrantedAuthority(roleName.getName()));
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
