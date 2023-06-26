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
public class AttendResponseDTO {

    private Long attendNo;
    private Long userNo;
    private Date godate;
    private Date leavedate;
    private LocalDate regdate;

//    AttendResponseDTO(final AttendEntity entity){
//        attendNo = entity.getAttendNo();
//    }

    public AttendResponseDTO(AttendEntity entity) {
        attendNo = entity.getAttendNo();
        userNo = entity.getMemberEntity().getUserNo();
        godate = entity.getGodate();
        leavedate = entity.getLeavedate();
        regdate = entity.getRegdate();
    }
}
