package com.ohalfmoon.firework.dto.sub;

import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import lombok.*;


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
