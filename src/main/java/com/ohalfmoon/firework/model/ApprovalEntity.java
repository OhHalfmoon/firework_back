package com.ohalfmoon.firework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "tbl_approval")
@DynamicInsert
@Builder
public class ApprovalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long approvalNo;

    @Column(nullable = false)
    private String approvalName;

    @OneToOne
    @JoinColumn(name = "formNo")
    private FormEntity formEntity;

    @OneToOne
    @JoinColumn(name = "lineNo")
    private MasterLineEntity masterLineEntity;

    @OneToOne
    @JoinColumn(name = "docboxNo")
    private DocboxEntity docboxEntity;

    @Column(nullable = false)
    private String approContent;

    @OneToOne
    @JoinColumn(name = "userNo")
    private MemberEntity memberEntity;

    @Column(nullable = false)
    private int save;

    @Column(nullable = false)
    private int approvalState;

    private Date regdate;

    private Date updatedate;
}
