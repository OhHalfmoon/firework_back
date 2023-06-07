package com.ohalfmoon.firework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_reservation")
@DynamicInsert
@Builder
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserveNo;

    @OneToOne
    @JoinColumn(name = "supplyNo")
    private SupplyEntity supplyEntity;

    @OneToOne
    @JoinColumn(name = "userNo")
    private MemberEntity memberEntity;

    @Column(nullable = false)
    private Date reserveStart;

    @Column(nullable = false)
    private Date reserveFinish;

    @Column(nullable = false)
    private int headcount;

    @Column(nullable = false)
    private String reserveComment;

    private Date regdate;

    private Date updatedate;


}
