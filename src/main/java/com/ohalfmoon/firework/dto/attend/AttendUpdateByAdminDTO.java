package com.ohalfmoon.firework.dto.attend;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ohalfmoon.firework.model.AttendEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
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
