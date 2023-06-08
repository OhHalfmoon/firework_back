package com.ohalfmoon.firework.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ohalfmoon.firework.model.MemberEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * packageName :  com.ohalfmoon.firework.dto.member
 * fileName : MemberUpdateDTO
 * author :  ycy
 * date : 2023-06-08
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-08                ycy             최초 생성
 */
@Data
@NoArgsConstructor
public class MemberUpdateDTO {
    private String email;
    private String phoneNum;
    private String name;
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private Date birthdate;
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private Date startdate;
    private Long deptNo;
    private Long positionNo;

    @Builder
    public MemberUpdateDTO(String email, String phoneNum, String name, Date birthdate, Date startdate, Long deptNo, Long positionNo) {
        this.email = email;
        this.phoneNum = phoneNum;
        this.name = name;
        this.birthdate = birthdate;
        this.startdate = startdate;
        this.deptNo = deptNo;
        this.positionNo = positionNo;
    }

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .email(email)
                .phoneNum(phoneNum)
                .name(name)
                .birthdate(birthdate)
                .startdate(startdate)
                .build();
    }
}
