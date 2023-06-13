package com.ohalfmoon.firework.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : Privilege
 * author         : banghansol
 * date           : 2023/06/13
 * description    : 권한 엔티티
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        방한솔            최초 생성
 */
@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "tbl_privilege")
public class PrivilegeEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long privilegeNo;
    private String privilegeName;

    @Builder
    public PrivilegeEntity(String privilegeName){
        this.privilegeName = privilegeName;
    }

    public void update(Long privilegeNo, String privilegeName) {
        this.privilegeNo = privilegeNo;
        this.privilegeName = privilegeName;
    }
}
