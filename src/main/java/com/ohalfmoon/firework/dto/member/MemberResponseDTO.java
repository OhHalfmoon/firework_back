package com.ohalfmoon.firework.dto.member;

import com.ohalfmoon.firework.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
public class MemberResponseDTO {
    private Long userNo;
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private String name;
    private boolean manager;
    private LocalDate birthdate;
    private LocalDate startdate;
    private Long deptNo;
    private String deptName;
    private Long positionNo;
    private String positionName;
    private String state;
    private String roleTitle;

    public MemberResponseDTO(MemberEntity entity) {
        userNo = entity.getUserNo();
        username = entity.getUsername();
        email = entity.getEmail();
        phoneNum = entity.getPhoneNum();
        name = entity.getName();
        manager = entity.isManager();
        birthdate = entity.getBirthdate();
        startdate = entity.getStartdate();
        deptNo = entity.getDeptEntity().getDeptNo();
        deptName = entity.getDeptEntity().getDeptName();
        positionNo = entity.getPositionEntity().getPositionNo();
        positionName = entity.getPositionEntity().getPositionName();
        state = entity.getState().getTitle();
        roleTitle = entity.getRoleName().getTitle();
    }
}
