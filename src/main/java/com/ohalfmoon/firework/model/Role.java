package com.ohalfmoon.firework.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * packageName :  com.ohalfmoon.firework.model
 * fileName : Role
 * author :  ycy
 * date : 2023-06-07
 * description : security권한
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-07                ycy             최초 생성
 */
@Getter
@RequiredArgsConstructor
public enum Role {

    ROLE_EMPLOYEE(0,"ROLE_EMPLOYEE", "사원"),
    ROLE_TL(1,"ROLE_TL", "팀장"),
    ROLE_CEO(2,"ROLE_CEO", "사장"),
    ROLE_ADMIN(3, "ROLE_ADMIN", "관리자"),
    ROLE_SECESSION(4, "ROLE_SECESSION", "탈퇴회원");

    private final int key;
    private final String name;
    private final String title;
}
