package com.ohalfmoon.firework.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_todo")
@ToString
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoNo;

    @Column(nullable = false)
    private boolean holiday;

    @ManyToOne
    @JoinColumn(name = "userNo")
    private MemberEntity memberEntity;

    @Column(nullable = false)
    private LocalDateTime todoStart;

    @Column(nullable = false)
    private LocalDateTime todoFinish;

    @Column(nullable = false)
    private String todoTitle;

    @Column(nullable = false)
    private String todoDetail;

    @Column(nullable = false)
    private LocalDate regdate;

    private LocalDate updatedate;

}
