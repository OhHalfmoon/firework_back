package com.ohalfmoon.firework.model;

import lombok.Getter;

import javax.persistence.*;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : RolePrivilegeEntity
 * author         : banghansol
 * date           : 2023/06/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        banghansol       최초 생성
 */
@Entity
@Getter
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
