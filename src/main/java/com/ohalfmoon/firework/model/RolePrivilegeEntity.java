package com.ohalfmoon.firework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : RolePrivilegeEntity
 * author         : 방한솔
 * date           : 2023/06/13
 * description    : 권한, 역할 연관테이블 엔티티
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        방한솔           최초 생성
 */
@Entity
@Getter
@NoArgsConstructor
@ToString(exclude = "roleEntity")
@Table(name = "tbl_role_privilege")
public class RolePrivilegeEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long relationNo;

    @ManyToOne
    @JoinColumn(name = "roleNo")
    private RoleEntity roleEntity;

    @ManyToOne
    @JoinColumn(name = "privilegeNo")
    private PrivilegeEntity privilegeEntity;
}
