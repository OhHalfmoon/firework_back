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

    private Long userNo;
    private String username;
    private String email;
    private String phoneNum;
    private String name;
    private boolean manager;
    private Date birthdate;
    private Date startdate;

    @Builder
    public MemberLoginDTO(Long userNo, String username, String email, String phoneNum, String name, boolean manager, Date birthdate, Date startdate) {
        this.userNo = userNo;
        this.username = username;
        this.email = email;
        this.phoneNum = phoneNum;
        this.name = name;
        this.manager = manager;
        this.birthdate = birthdate;
        this.startdate = startdate;
    }

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .userNo(userNo)
                .username(username)
                .email(email)
                .phoneNum(phoneNum)
                .name(name)
                .manager(manager)
                .birthdate(birthdate)
                .startdate(startdate)
                .build();
    }
}

