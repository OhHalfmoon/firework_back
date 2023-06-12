package com.ohalfmoon.firework.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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

    GUEST("ROLE_GUEST", "손님"),
    EMPLOYEE("ROLE_EMPLOYEE", "사원"),
    TL("ROLE_TL", "팀장"),
    CEO("ROLE_CEO", "사장");


    private final String key;
    private final String title;
}
