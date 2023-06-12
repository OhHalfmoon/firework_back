package com.ohalfmoon.firework.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : PositionEntity
 * author         : 양찬용
 * date           : 2023/06/01
 * description    : 직급 Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/06        양찬용           최초 생성
 *
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_position")
@DynamicInsert
@ToString
@Builder
public class PositionEntity {
    @Id
    private Long positionNo; // 직급 번호
    private String positionName; // 직급명
    private Date regdate; // 등록일
    private Date updatedate; // 업데이트일
}
