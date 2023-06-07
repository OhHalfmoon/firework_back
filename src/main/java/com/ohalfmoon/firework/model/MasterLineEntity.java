package com.ohalfmoon.firework.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : MasterLineEntity
 * author         : 이지윤
 * date           : 2023/06/07
 * description    : 결재 선에 필요한 정보(기안자, 결재선 명) Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/05        이지윤           최초 생성
 */

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name="tbl_master_line")
public class MasterLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineNo;

    @Column(nullable = false)
    private String lineName;

    private Long userNo;

    private Date regdate;

    private Date updatedate;

    public void update(String lineName) {
        this.lineName = lineName;
    }
}
