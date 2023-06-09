package com.ohalfmoon.firework.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import javax.persistence.*;

/**
 * packageName    : com.ohalfmoon.firework.entity
 * fileName       : SubLineEntity
 * author         : 이지윤
 * date           : 2023/06/07
 * description    : 결재 선에 필요한 정보(결재자, 결재 순서)Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        이지윤           최초 생성
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
@Table(name="tbl_sub_line")
public class SubLineEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subLineNo;

    @Column(nullable = false)
    private Integer orderLevel;

    @ManyToOne
    @JoinColumn(name = "lineNo")
    private MasterLineEntity lineNo;

    @ManyToOne
    @JoinColumn(name = "userNo")
    private MemberEntity userNo;

    public void update(Integer orderLevel, Long userNo) {
        this.orderLevel = orderLevel; // 결재 순서
        this.userNo = MemberEntity.builder()
                .userNo(userNo)
                .build(); // 결재자
    }
}
