package com.ohalfmoon.firework.dto.master;


import com.ohalfmoon.firework.model.FormEntity;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MasterLineResponseDTO {
    private Long lineNo;
    private String lineName;
    private Long userNo;
    private LocalDateTime regdate;

    public MasterLineResponseDTO(final MasterLineEntity entity) {
        this.lineNo = entity.getLineNo();
        this.lineName = entity.getLineName();
        this.userNo = entity.getUserNo().getUserNo();
        this.regdate = entity.getRegdate();
    }

    public MasterLineEntity toEntity(){
        return MasterLineEntity.builder()
                .lineNo(lineNo)
                .build();
    }

}
