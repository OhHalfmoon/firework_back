package com.ohalfmoon.firework.dto.attend;

import com.ohalfmoon.firework.model.AttendEntity;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AttendUpdateDTO {
    private Date leavedate;

    public AttendUpdateDTO(final AttendEntity entity) {
        this.leavedate = entity.getLeavedate();
    }

    public AttendEntity toEntity(){
        return AttendEntity.builder()
                .leavedate(leavedate)
                .build();
    }
}
