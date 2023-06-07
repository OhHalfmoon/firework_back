package com.ohalfmoon.firework.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
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

    @CreatedDate
    private LocalDateTime regdate;

    @ManyToOne
    @JoinColumn(name = "boardNo")
    private BoardEntity boardNo;

    @ManyToOne
    @JoinColumn(name = "approvalNo")
    private ApprovalEntity approvalNo;

}
