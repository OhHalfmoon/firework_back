package com.ohalfmoon.firework.dto.sub;

import com.ohalfmoon.firework.model.SubLineEntity;
import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SubLineResponseDTO {
    private Long subLineNo;
    public SubLineResponseDTO(final SubLineEntity entity) {
        this.subLineNo = entity.getSubLineNo();
    }

    public SubLineEntity toEntity(){
        return SubLineEntity.builder()
                .subLineNo(subLineNo)
                .build();
    }
}
