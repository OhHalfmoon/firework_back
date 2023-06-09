package com.ohalfmoon.firework.dto.master;


import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MasterLineResponseDTO {
    private Long lineNo;

    public MasterLineResponseDTO(final MasterLineEntity entity) {
        this.lineNo = entity.getLineNo();
    }

    public MasterLineEntity toEntity(){
        return MasterLineEntity.builder()
                .lineNo(lineNo)
                .build();
    }

}
