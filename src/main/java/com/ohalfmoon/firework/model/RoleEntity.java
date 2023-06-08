package com.ohalfmoon.firework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

/**
 * packageName :  com.ohalfmoon.firework.model
 * fileName : RoleEntity
 * author :  ycy
 * date : 2023-06-07
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-07                ycy             최초 생성
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_role")
@DynamicInsert
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminNo; // 권한번호

    @ManyToOne()
    @JoinColumn(name = "userNo")
    private MemberEntity memberEntity; // 회원번호(fk)

    @Column(nullable = false)
    private String authName; // 권한 이름

    @Column(nullable = false)
    private boolean roleCheck; // 권한 허용 여부

    private Date regdate;

    private Date updatedate;
}