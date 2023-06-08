package com.ohalfmoon.firework.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
public class SubLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subLineNo;

    @Column(nullable = false)
    private Integer orderLevel;

    @Column(nullable = false)
    private Long lineNo;

    @Column(nullable = false)
    private Long userNo;

    private Date regdate;
    private Date updatedate;

}
