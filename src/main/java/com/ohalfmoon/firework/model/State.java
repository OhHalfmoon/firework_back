package com.ohalfmoon.firework.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName :  com.ohalfmoon.firework.model
 * fileName : State
 * author :  ycy
 * date : 2023-06-22
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-22                ycy             최초 생성
 */
@Getter
@RequiredArgsConstructor
public enum State {

    WATING(0,"승인대기"),
    APPROVAL(1, "승인완료"),
    SECESSION(2, "퇴사자");

    
    private final int key;
    private final String title;
}
