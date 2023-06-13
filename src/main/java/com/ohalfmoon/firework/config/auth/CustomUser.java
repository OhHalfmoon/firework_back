package com.ohalfmoon.firework.config.auth;

import com.ohalfmoon.firework.dto.member.MemberLoginDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * packageName :  com.ohalfmoon.firework.config.auth
 * fileName : UserService
 * author :  ycy
 * date : 2023-06-13
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-13                ycy             최초 생성
 */
@Getter
public class CustomUser extends User {

    private MemberLoginDTO memberLoginDTO;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

//    public CustomUser(MemberLoginDTO dto) {
//        super(dto.getUsername(), dto.getPassword());
//        this.memberLoginDTO = dto;
//    }


}
