package com.ohalfmoon.firework.dto;

import com.ohalfmoon.firework.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Member;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String username;
    private String password;
    private String email;
    private String phoneNum;
    private Long deptNo;
    private Long positionNo;
    private String name;
    private boolean manager;
    private Date birthdate;
    private String authProvider;
    private String memberSign;
    private Date startdate;
    private Date enddate;
    private int state;
    private Date regdate;
    private Date updatedate;

    @Builder
    public MemberDTO(String username
            , String password
            , String email
            , String phoneNum
            , Long deptNo
            , Long positionNo
            , String name
            , Date birthdate
            , Date startdate
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.deptNo = deptNo;
        this.positionNo = positionNo;
        this.name = name;
        this.birthdate = birthdate;
        this.startdate = startdate;
    }
    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .username(username)
                .password(password)
                .email(email)
                .phoneNum(phoneNum)
//                .deptNo(deptNo)
//                .positionNo(positionNo)
                .name(name)
                .birthdate(birthdate)
                .startdate(startdate)
                .build();
    }

}
