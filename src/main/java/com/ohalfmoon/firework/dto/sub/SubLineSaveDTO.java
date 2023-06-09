package com.ohalfmoon.firework.dto.sub;

import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubLineSaveDTO {
    private Integer orderLevel;
    private Long lineNo;
    private Long userNo;
    private LocalDateTime regdate;

    public SubLineSaveDTO(final SubLineEntity entity) {
        this.orderLevel = entity.getOrderLevel();
        this.lineNo = entity.getLineNo().getLineNo();
        this.userNo = entity.getUserNo().getUserNo();
        this.regdate = entity.getRegdate();
    }

    public SubLineEntity toEntity(){
        return SubLineEntity.builder()
                .orderLevel(orderLevel)
                .lineNo(MasterLineEntity.builder()
                        .lineNo(lineNo)
                        .build())
                .userNo(MemberEntity.builder()
                        .userNo(userNo)
                        .build())
                .build();
    }
}
