package com.ohalfmoon.firework.dto.member;

import com.ohalfmoon.firework.model.MemberEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * packageName :  com.ohalfmoon.firework.dto.member
 * fileName : MemberResponseDTO
 * author :  ycy
 * date : 2023-06-09
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-09                ycy             최초 생성
 */
@Data
@NoArgsConstructor
public class MemberResponseDTO {
    private Long userNo;
    private String username;
    private String email;
    private String phoneNum;
    private String name;
    private boolean manager;
    private Date birthdate;
    private Date startdate;

    public MemberResponseDTO(MemberEntity entity) {
        userNo = entity.getUserNo();
        username = entity.getUsername();
        email = entity.getEmail();
        phoneNum = entity.getPhoneNum();
        name = entity.getName();
        manager = entity.isManager();
        birthdate = entity.getBirthdate();
        startdate = entity.getStartdate();
    }
}
