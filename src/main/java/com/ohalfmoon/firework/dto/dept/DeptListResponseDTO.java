package com.ohalfmoon.firework.dto.dept;

import com.ohalfmoon.firework.model.DeptEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 * 2023-06-23             방한솔             생성자
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

    @Builder
    public DeptListResponseDTO(Long deptNo, String deptName) {
        this.deptNo = deptNo;
        this.deptName = deptName;
    }
}
