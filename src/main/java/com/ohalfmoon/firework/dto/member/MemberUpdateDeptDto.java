package com.ohalfmoon.firework.dto.member;

import lombok.Data;
import lombok.ToString;

/**
 * packageName    : com.ohalfmoon.firework.dto.member
 * fileName       : MemberUpdateDeptDto
 * author         : banghansol
 * date           : 2023/06/27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/27        banghansol       최초 생성
 */
@ToString
@Data
public class MemberUpdateDeptDto {
    private Long userNo;
    private Long deptNo;
}
