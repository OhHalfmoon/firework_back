package com.ohalfmoon.firework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_attend")
@DynamicInsert
@Builder
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

    @Column(nullable = false)
    private LocalDate regdate;

    public void update(Date leavedate) {
        this.leavedate = leavedate;
    }

    public void updateByAdmin(Date leavedate) { this.leavedate = leavedate; }

}
