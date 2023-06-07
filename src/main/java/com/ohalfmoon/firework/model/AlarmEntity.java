package com.ohalfmoon.firework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
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
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_alarm")
@DynamicInsert
@Builder
public class AlarmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alarmNo;

    @ManyToOne
    @JoinColumn(name = "alarmReceiver")
    private MemberEntity alarmReceiver;

    @Column(nullable = false)
    private boolean alarmCheck;

    @Column(nullable = false)
    private String alarmCategory;

    @Column(nullable = false)
    private String alarmTitle;

    private Date regdate;

    @ManyToOne
    @JoinColumn(name = "boardNo")
    private BoardEntity boardNo;

    @ManyToOne
    @JoinColumn(name = "approvalNo")
    private ApprovalEntity approvalNo;

}
