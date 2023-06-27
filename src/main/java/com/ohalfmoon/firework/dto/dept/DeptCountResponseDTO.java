package com.ohalfmoon.firework.dto.dept;

import com.ohalfmoon.firework.model.DeptEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * packageName    : com.ohalfmoon.firework.dto.dept
 * fileName       : DeptCountResponseDTO
 * author         : banghansol
 * date           : 2023/06/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/26        banghansol       최초 생성
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class DeptCountResponseDTO extends DeptListResponseDTO {
    private int count;

    public DeptCountResponseDTO(Long deptNo, String deptName, int count) {
        super(deptNo, deptName);
        this.count = count;
    }
}
