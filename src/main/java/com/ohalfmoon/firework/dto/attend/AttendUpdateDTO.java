package com.ohalfmoon.firework.dto.attend;

import com.ohalfmoon.firework.model.AttendEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AttendUpdateDTO {
    private Date leavedate;
    private Long attendNo;
    private LocalDate regdate;

    public AttendUpdateDTO(final AttendEntity entity) {
        attendNo = entity.getAttendNo();
        leavedate = entity.getLeavedate();
        regdate = entity.getRegdate();
    }

    public AttendEntity toEntity(){
        return AttendEntity.builder()
                .leavedate(leavedate)
                .regdate(LocalDate.now())
                .build();
    }
}
