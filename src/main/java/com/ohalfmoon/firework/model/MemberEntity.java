package com.ohalfmoon.firework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_member")
@DynamicInsert
@Builder
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNum;

    @ManyToOne
    @JoinColumn(name = "deptNo")
    private DeptEntity deptEntity;

    @ManyToOne
    @JoinColumn(name = "positionNo")
    private PositionEntity positionEntity;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean manager;

    @Column(nullable = true)
    private Date birthdate;

    private String authProvider;

    private String memberSign;

    @Column(nullable = false)
    private Date startdate;

    private Date enddate;

    @Column(nullable = false)
    private int state;

    private Date regdate;

    private Date updatedate;

    public void updateDeptNo(DeptEntity deptEntity) {
        this.deptEntity = deptEntity;
    }

    public void updatePositionNo(PositionEntity positionEntity) {
        this.positionEntity = positionEntity;
    }

    @Builder
    public MemberEntity(String username
            , String password
            , String email
            , String phoneNum
//            , Long deptNo
            , DeptEntity deptEntity
            , Long positionNo
            , String name
            , Date birthdate
            , Date startdate
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.deptEntity = deptEntity;
//        this.positionEntity.getPositionNo() =
        this.name = name;
        this.birthdate = birthdate;
        this.startdate = startdate;

    }

}
