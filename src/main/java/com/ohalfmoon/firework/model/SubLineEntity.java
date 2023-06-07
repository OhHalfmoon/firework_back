package com.ohalfmoon.firework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : SubLineEntity
 * author         : 이지윤
 * date           : 2023/06/07
 * description    : 결재선 설정 시 필요한 정보(결재자, 결재 순서)DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/05        이지윤           최초 생성
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
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

    @Column(nullable = false)
    private Date regdate;
    private Date updatedate;

}
