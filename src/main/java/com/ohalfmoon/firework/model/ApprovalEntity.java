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

    @ManyToOne
    @JoinColumn(name = "formNo")
    private FormEntity formEntity;

    @ManyToOne
    @JoinColumn(name = "lineNo")
    private MasterLineEntity masterLineEntity;

    @ManyToOne
    @JoinColumn(name = "docboxNo")
    private DocboxEntity docboxEntity;

    @Column(nullable = false)
    private String approContent;

    @ManyToOne
    @JoinColumn(name = "userNo")
    private MemberEntity memberEntity;

    @Column(nullable = false)
    private int save;

    @Column(nullable = false)
    private int approvalState;

    private Date regdate;

    private Date updatedate;
}
