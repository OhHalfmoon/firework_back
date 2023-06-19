package com.ohalfmoon.firework.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_attend")
@DynamicInsert
@Builder
@ToString
public class AttendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendNo;

    @ManyToOne
    @JoinColumn(name = "userNo")
    private MemberEntity memberEntity;

    @Column(nullable = false)
    private Date godate;

    private Date leavedate;

    public void update(Date leavedate) {
        this.leavedate = leavedate;
    }

}