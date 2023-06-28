package com.ohalfmoon.firework.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : AlarmEntity
 * author         : 우성준
 * date           : 2023/06/07
 * description    : 알림 Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        우성준           최초 생성
 * 2023/06/08        우성준           regdate 타입 localdatetime -> localdate
 * 2023/06/09        우성준           regdate 타입 localdate -> localdatetime
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_alarm")
@DynamicInsert
@Builder
@EqualsAndHashCode()
@ToString
@EntityListeners(AuditingEntityListener.class)
public class AlarmEntity {

    // 알림 일련번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alarmNo;

    // 알림 받을 사원객체(사원일련번호로)
    @ManyToOne
    @JoinColumn(name = "alarmReceiver")
    private MemberEntity alarmReceiver;

    // 알림 확인 여부
    @Column(nullable = false)
    private boolean alarmCheck;

    // 알림 카테고리
    @Column(nullable = false)
    private String alarmCategory;

    // 알림 제목
    @Column(nullable = false)
    private String alarmTitle;

    // 알림 생성시간
    @CreatedDate
    private LocalDateTime regdate;

    // 알림을 생성할 게시글객체(게시글일련번호로)
    @ManyToOne
    @JoinColumn(name = "boardNo")
    private BoardEntity boardNo;

    // 알림을 생성할 결재객체(결재일련번호로)
    @ManyToOne
    @JoinColumn(name = "approvalNo")
    private ApprovalEntity approvalNo;

    // 알림 확인 업데이트
    public void update(boolean alarmCheck){
        this.alarmCheck = alarmCheck;
    }
}
