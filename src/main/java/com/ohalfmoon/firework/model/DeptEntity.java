package com.ohalfmoon.firework.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : DeptEntity
 * author         : 양찬용
 * date           : 2023/06/05
 * description    : 부서 Entity
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
@Table(name = "tbl_dept")
@DynamicInsert
@ToString
@Builder
public class DeptEntity {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long deptNo; // 부서번호
    private String deptName; // 부서명
    private Date regdate; // 등록일
    private Date updatedate; // 수정일
}
