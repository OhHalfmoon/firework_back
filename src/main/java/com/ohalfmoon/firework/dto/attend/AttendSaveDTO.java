package com.ohalfmoon.firework.dto.attend;


import com.ohalfmoon.firework.model.AttendEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AttendSaveDTO {
    private Date godate;
    private Long userNo;

    public AttendSaveDTO(final AttendEntity entity) {
        godate = entity.getGodate();
        userNo = entity.getMemberEntity().getUserNo();
    }

    public AttendEntity toEntity() {
        return AttendEntity.builder()
                .godate(godate)
                .memberEntity(MemberEntity.builder()
                        .userNo(userNo)
                        .build())
                .build();
    }
}
