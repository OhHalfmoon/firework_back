package com.ohalfmoon.firework.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ohalfmoon.firework.model.MemberEntity;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

/**
 * packageName :  com.ohalfmoon.firework.dto.member
 * fileName : MemberDTO
 * author :  ycy
 * date : 2023-06-09
 * description : view에서 전달받은 회원가입 정보를 매핑하여 entity로 전송
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-01                ycy             최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private Long userNo;
    private String username;
    private String password;
    private String email;
    private String phoneNum;
    private Long deptNo;
    private Long positionNo;
    private String name;
    private boolean manager;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private Date birthdate;
    private String authProvider;
    private String memberSign;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private Date startdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private Date enddate;
    private int state;
    private Date regdate;
    private Date updatedate;

    @Builder
    public MemberDTO(String username
            , String password
            , String email
            , String phoneNum
            , String name
            , Date birthdate
            , Date startdate
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
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
                .name(name)
                .birthdate(birthdate)
                .startdate(startdate)
                .build();
    }

}