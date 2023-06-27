package com.ohalfmoon.firework.dto.attend;

import com.ohalfmoon.firework.model.AttendEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * packageName :  com.ohalfmoon.firework.dto.attend
 * fileName : AttendUpdateByAdminDTO
 * author :  ycy
 * date : 2023-06-26
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-26                ycy             최초 생성
 */

@Data
@NoArgsConstructor
@Builder
@ToString
public class AttendUpdateByAdminDTO {
    private Long attendNo;
    private Date leavedate;

    public AttendUpdateByAdminDTO(Long attendNo, Date leavedate) {
        this.attendNo = attendNo;
        this.leavedate = leavedate;
    }

    public AttendEntity toEntity() {
        return AttendEntity.builder()
                .leavedate(leavedate)
                .build();
    }
}
