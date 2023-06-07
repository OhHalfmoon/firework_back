package com.ohalfmoon.firework.dto;

import com.ohalfmoon.firework.model.SubLineEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : SubLineDTO
 * author         : 이지윤
 * date           : 2023/06/07
 * description    : 결재 선에 필요한 정보(결재자, 결재 순서)DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        이지윤           최초 생성
 */

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
