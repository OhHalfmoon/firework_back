package com.ohalfmoon.firework.dto.position;

import com.ohalfmoon.firework.model.PositionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName :  com.ohalfmoon.firework.dto.position
 * fileName : PositionListResponseDTO
 * author :  ycy
 * date : 2023-06-09
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-09                ycy             최초 생성
 */
@NoArgsConstructor
@Data
public class PositionListResponseDTO {
    private Long positionNo;
    private String positionName;

    public PositionListResponseDTO(PositionEntity entity) {
        positionNo = entity.getPositionNo();
        positionName = entity.getPositionName();
    }
}
