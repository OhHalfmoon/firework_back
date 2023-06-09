package com.ohalfmoon.firework.dto;

import com.ohalfmoon.firework.model.AlarmEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : AlarmResponseDto
 * author         : 우성준
 * date           : 2023/06/07
 * description    : 알림조회 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        우성준           최초 생성
 * 2023/06/09        우성준           regdate 타입 localdate -> localdatetime
 */
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AlarmResponseDto {
    private Long alarmNo;

    private Long userNo;

    private String name;

    private boolean alarmCheck;

    private String alarmCategory;

    private String alarmTitle;

    private LocalDateTime regdate;

    private Long boardNo;

    private Long approvalNo;

    public AlarmResponseDto(AlarmEntity entity) {
        alarmNo = entity.getAlarmNo();
        userNo = entity.getAlarmReceiver().getUserNo();
        name = entity.getAlarmReceiver().getName();
        alarmCheck = entity.isAlarmCheck();
        alarmCategory = entity.getAlarmCategory();
        alarmTitle = entity.getAlarmTitle();
        boardNo = entity.getBoardNo() == null ? null : entity.getBoardNo().getBoardNo();
        approvalNo = entity.getApprovalNo() == null ? null : entity.getApprovalNo().getApprovalNo();
        regdate = entity.getRegdate();
    }

}
