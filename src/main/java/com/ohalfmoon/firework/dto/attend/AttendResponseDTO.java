package com.ohalfmoon.firework.dto.attend;

import com.ohalfmoon.firework.model.AttendEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AttendResponseDTO {
    private Long attendNo;

    AttendResponseDTO(final AttendEntity entity){
        attendNo = entity.getAttendNo();
    }
}
