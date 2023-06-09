package com.ohalfmoon.firework.dto.sub;

import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubLineUpdateDTO {
    private Integer orderLevel;
    private Long userNo;

    public SubLineUpdateDTO(final SubLineEntity entity) {
        this.orderLevel = entity.getOrderLevel();
        this.userNo = entity.getUserNo().getUserNo();
    }

    public SubLineEntity toEntity(){
        return SubLineEntity.builder()
                .orderLevel(orderLevel)
                .userNo(MemberEntity.builder()
                        .userNo(userNo)
                        .build())
                .build();
    }

}
