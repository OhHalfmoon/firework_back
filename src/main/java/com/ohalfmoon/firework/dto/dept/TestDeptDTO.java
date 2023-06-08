package com.ohalfmoon.firework.dto.dept;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName :  com.ohalfmoon.firework.dto.form
 * fileName : DeptDTO
 * author :  ycy
 * date : 2023-06-07
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-07                ycy             최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TestDeptDTO {
    private Long deptNo;
    private String deptName;

}
