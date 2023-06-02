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
public class MemberEntity {
    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNum;

//    private Long deptNo;
    @ManyToOne
    @JoinColumn(name = "deptNo")
    private DeptEntity deptEntity;

    @ManyToOne
    @JoinColumn(name = "positionNo")
    private PositionEntity positionEntity;
//    private Long positionNo;

    @Column(nullable = false)
    private String name;

    private boolean manager;

    @Column(nullable = true)
    private Date birthdate;
//    @Column(name = "auth_provider")
    private String authProvider;

    private String memberSign;

    private Date startdate;

    private Date enddate;

    private int state;

    private Date regdate;

    private Date updatedate;

    @Builder
    public MemberEntity(String username
            , String password
            , String email
            , String phoneNum
//            , Long deptNo
//            , Long positionNo
            , String name
            , Date birthdate
            , Date startdate
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
//        this.deptNo = deptNo;
//        this.positionNo = positionNo;
        this.name = name;
        this.birthdate = birthdate;
        this.startdate = startdate;

    }

}
