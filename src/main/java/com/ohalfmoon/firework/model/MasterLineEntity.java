package com.ohalfmoon.firework.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;


/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : MasterLineEntity
 * author         : 이지윤
 * date           : 2023/06/07
 * description    : 결재 선에 필요한 정보(기안자, 결재선 명) Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        이지윤           최초 생성
 */

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@DynamicInsert
@Table(name="tbl_master_line")

public class MasterLineEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineNo;

    @Column(nullable = false)
    private String lineName;

    @ManyToOne
    @JoinColumn(name="userNo")
    private MemberEntity userNo;

    public void update(String lineName) {
        this.lineName = lineName;
    }
}
