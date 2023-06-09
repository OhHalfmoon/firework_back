package com.ohalfmoon.firework.dto.member;

import com.ohalfmoon.firework.model.MemberEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * packageName :  com.ohalfmoon.firework.dto.member
 * fileName : MemberLoginDTO
 * author :  ycy
 * date : 2023-06-09
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-09                ycy             최초 생성
 */
@Data
@Builder
@NoArgsConstructor
public class MemberLoginDTO {

    private String username;
    private String password;

    public MemberLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

