package com.ohalfmoon.firework.dto;

import com.ohalfmoon.firework.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : AlarmSaveDto
 * author         : 우성준
 * date           : 2023/06/07
 * description    : 알림등록 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        우성준           최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlarmSaveDto {
    private Long userNo;

    private String alarmCategory;

    private String alarmTitle;

    @OneToOne
    @JoinColumn(name = "boardNo")
    private BoardEntity boardNo;

    @OneToOne
    @JoinColumn(name = "approvalNo")
    private ApprovalEntity approvalNo;

    public AlarmEntity toEntity() {
        return AlarmEntity.builder()
                .alarmReceiver(MemberEntity.builder().userNo(userNo).build())
                .alarmCategory(alarmCategory)
                .alarmTitle(alarmTitle)
                .build();
    }
}
