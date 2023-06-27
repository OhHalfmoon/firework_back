package com.ohalfmoon.firework.model;

import com.ohalfmoon.firework.dto.dept.DeptUpdateDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
@ToString(exclude = "memberList")
@Builder
public class DeptEntity extends BaseTimeEntity {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long deptNo; // 부서번호
    private String deptName; // 부서명
//    private Date regdate; // 등록일
//    private Date updatedate; // 수정일

    @OneToMany
    @JoinColumn(name = "deptNo")
    List<MemberEntity> memberList = new ArrayList<>();

    public void update(DeptUpdateDto dto){
        deptNo = dto.getDeptNo();
        deptName = dto.getDeptName();
    }
}
