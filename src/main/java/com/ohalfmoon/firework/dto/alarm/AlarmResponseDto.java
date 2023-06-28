package com.ohalfmoon.firework.dto.alarm;

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
 * 2023/06/08        우성준           regdate 타입 localdatetime -> localdate
 * 2023/06/09        우성준           regdate 타입 localdate -> localdatetime
 */
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AlarmResponseDto {

    // 알림일련번호
    private Long alarmNo;

    // 알림 받는 사원일련번호
    private Long userNo;

    // 알림 받는 사원이름
    private String name;

    // 알림 수신 여부
    private boolean alarmCheck;

    // 알림 카테고리
    private String alarmCategory;

    // 알림 제목
    private String alarmTitle;

    // 알림 생성 시간
    private LocalDateTime regdate;

    // 알림을 만들어 낸 게시판일련번호
    private Long boardNo;

    // 알림을 만들어 낸 결재일련번호
    private Long approvalNo;

    // 엔티티를 조회dto로 변환
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
