package com.ohalfmoon.firework.dto;

import com.ohalfmoon.firework.model.SubLineEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubLineDTO {
    private Long subLineNo;
    private Integer orderLevel;
    private Long lineNo;
    private Long userNo;
    private Date regdate;
    private Date updatedate;

    public SubLineDTO(final SubLineEntity entity) {
        this.subLineNo = entity.getSubLineNo();
        this.orderLevel = entity.getOrderLevel();
        this.lineNo = entity.getLineNo();
        this.userNo = entity.getUserNo();
        this.regdate = entity.getRegdate();
        this.updatedate = entity.getUpdatedate();
    }

    public SubLineEntity toEntity(){
        return SubLineEntity.builder()
                .subLineNo(subLineNo)
                .orderLevel(orderLevel)
                .lineNo(lineNo)
                .userNo(userNo)
                .build();
    }
}
