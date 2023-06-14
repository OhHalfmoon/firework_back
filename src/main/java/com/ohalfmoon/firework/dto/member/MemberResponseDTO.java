package com.ohalfmoon.firework.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.PositionEntity;
import com.ohalfmoon.firework.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * packageName :  com.ohalfmoon.firework.dto.member
 * fileName : MemberResponseDTO
 * author :  ycy
 * date : 2023-06-09
 * description : session에 저장될 회원 정보를 매핑하여 view로 전달
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-09                ycy             최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDTO {
    private Long userNo;
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private String name;
    private boolean manager;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private Date birthdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private Date startdate;
    private DeptEntity deptEntity;
    private PositionEntity positionEntity;
    private RoleEntity roleEntity;

    public MemberResponseDTO(MemberEntity entity) {
        userNo = entity.getUserNo();
        username = entity.getUsername();
        email = entity.getEmail();
        phoneNum = entity.getPhoneNum();
        name = entity.getName();
        manager = entity.isManager();
        birthdate = entity.getBirthdate();
        startdate = entity.getStartdate();
        deptEntity = entity.getDeptEntity();
        positionEntity = entity.getPositionEntity();
    }
}
