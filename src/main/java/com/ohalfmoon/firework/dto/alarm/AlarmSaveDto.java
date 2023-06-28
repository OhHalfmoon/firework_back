package com.ohalfmoon.firework.dto.alarm;

import com.ohalfmoon.firework.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.ToOne;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * 2023/06/08        우성준           OneToOne어노테이션 제거, 생성자 삼항연산추가(게시판 혹은 결재 관련 요청을 받기 위해)
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlarmSaveDto {

    // 알림 받는 사원일련번호
    private Long userNo;

    // 알림 카테고리
    private String alarmCategory;

    // 알림 제목
    private String alarmTitle;

    // 알림 만들어 낼 게시글 일련번호
    private Long boardNo;

    // 알림 만들어 낼 결재 일련번호
    private Long approvalNo;

    // 알림엔티티로 변환
    public AlarmEntity toEntity(){
        return AlarmEntity.builder()
                .alarmReceiver(MemberEntity.builder().userNo(userNo).build())
                .alarmCategory(alarmCategory)
                .alarmTitle(alarmTitle)
                .boardNo(boardNo == null ? null : BoardEntity.builder().boardNo(boardNo).build())
                .approvalNo(approvalNo == null ? null :ApprovalEntity.builder().approvalNo(approvalNo).build())
                .build();
    }

}
