package com.ohalfmoon.firework.dto.sub;

import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import lombok.*;

/**
 * packageName    : com.ohalfmoon.firework.dto.sub
 * fileName       : SubLineResponseDTO
 * author         : 이지윤
 * date           : 2023/06/08
 * description    : 결재 선 조회 시 필요한 정보 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/08        이지윤           최초 생성
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SubLineResponseDTO {
    private Long subLineNo;
    private Integer orderLevel;
    private Long lineNo;
    private Long userNo;

    public SubLineResponseDTO(final SubLineEntity entity) {
        this.subLineNo = entity.getSubLineNo();
        this.orderLevel = entity.getOrderLevel();
        this.lineNo = entity.getMasterLineEntity().getLineNo();
        this.userNo = entity.getMemberEntity().getUserNo();
    }

    public SubLineEntity toEntity(){
        return SubLineEntity.builder()
                .subLineNo(subLineNo)
                .build();
    }

}
