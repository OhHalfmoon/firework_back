package com.ohalfmoon.firework.dto.attend;


import com.ohalfmoon.firework.model.AttendEntity;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AttendSaveGoToWorkDTO {
    private Date godate;

    public AttendSaveGoToWorkDTO(final AttendEntity entity) {
        this.godate = entity.getGodate();
    }

    public AttendEntity toEntity(){
        return AttendEntity.builder()
                .godate(godate)
                .build();
    }
}
