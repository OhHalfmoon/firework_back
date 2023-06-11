package com.ohalfmoon.firework.dto.dept;

import com.ohalfmoon.firework.model.DeptEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * packageName :  com.ohalfmoon.firework.dto.form
 * fileName : DeptDTO
 * author :  ycy
 * date : 2023-06-07
 * description : 부서 ResponseDTO
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-07                ycy             최초 생성
 */
@NoArgsConstructor
@Data
public class DeptListResponseDTO {
    private Long deptNo;
    private String deptName;

    /**
     * 부서 List Response
     * @param entity the entity
     */
    public DeptListResponseDTO(DeptEntity entity) {
        deptNo = entity.getDeptNo();
        deptName = entity.getDeptName();
    }
}
