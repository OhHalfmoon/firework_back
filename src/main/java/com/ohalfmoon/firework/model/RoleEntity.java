package com.ohalfmoon.firework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * packageName :  com.ohalfmoon.firework.model
 * fileName : RoleEntity
 * author :  ycy
 * date : 2023-06-07
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-07                ycy             최초 생성
 * 2023-06-13             방한솔
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_role")
@DynamicInsert
public class RoleEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleNo; // 권한번호

    @Column(nullable = false)
    private String roleName; // 권한 이름

    @OneToMany(mappedBy = "roleEntity")
    List<RolePrivilegeEntity> rolePrivilegeList;
}
