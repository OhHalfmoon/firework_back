package com.ohalfmoon.firework.dto.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.PositionEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.swing.text.Position;
import java.lang.reflect.Member;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private String username;
    private String password;
    private String email;
    private String phoneNum;
    private Long deptNo;
    private Long positionNo;
    private String name;
    private boolean manager;
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private Date birthdate;
    private String authProvider;
    private String memberSign;
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private Date startdate;
    @DateTimeFormat(pattern = "yyyyMMdd")
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
