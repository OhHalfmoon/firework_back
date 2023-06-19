package com.ohalfmoon.firework.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : MemberEntity
 * author         : 양찬용
 * date           : 2023/06/01
 * description    : 회원 Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/01        양찬용           최초 생성
 *
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_member")
@DynamicInsert
@Builder
@ToString
public class MemberEntity {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long userNo; // 회원 번호

    @Column(unique = true)
    private String username; // 회원 id

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String email; // email

    @Column(nullable = false)
    private String phoneNum; // 연락처

    @ManyToOne // foreign key
    @JoinColumn(name = "deptNo") // foreign key column name
    private DeptEntity deptEntity; // 부서번호

    @ManyToOne // foreign key
    @JoinColumn(name = "positionNo") // foreign key column name
    private PositionEntity positionEntity; // 직급번호

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Role roleName;

    @Column(nullable = false)
    private String name; // 이름

    @Column(nullable = false)
    private boolean manager; // 관리자 여부

    @Column(nullable = true)
    private Date birthdate; // 생일

    private String authProvider; // login 제공자

    private String memberSign; // 전자서명 (base64)

    @Column(nullable = false)
    private Date startdate; // 입사일

    private Date enddate; // 퇴사일

    @Column(nullable = false)
    private int state; // 가입승인여부

    private Date regdate; // 등록일자

    private Date updatedate; // 업데이트 일자

    /**
     * Update dept no.
     *
     * @param deptEntity the dept entity
     */
    public void updateDeptNo(DeptEntity deptEntity) {
        this.deptEntity = deptEntity;
    }

    /**
     * Update position no.
     *
     * @param positionEntity the position entity
     */
    public void updatePositionNo(PositionEntity positionEntity) {
        this.positionEntity = positionEntity;
    }


    /**
     * 비밀번호 변경
     *
     * @param password the password
     */
    public void updatePw(String password) {
        this.password = password;
    }

    /**
     * 회원 정보 수정
     *
     * @param email     the email
     * @param phoneNum  the phone num
     * @param name      the name
     * @param birthdate the birthdate
     * @param startdate the startdate
     */
    public void update(String email, String phoneNum, String name, Date birthdate, Date startdate) {
        this.email = email;
        this.phoneNum = phoneNum;
        this.name = name;
        this.birthdate = birthdate;
        this.startdate = startdate;
    }

    /**
     * 가입 승인, 탈퇴
     *
     * @param state the state
     */
    public void updateState(Long userNo, int state) {
        this.userNo = userNo;
        this.state = state;
    }


    /**
     * Member Builder
     *
     * @param username  the username
     * @param password  the password
     * @param email     the email
     * @param phoneNum  the phone num
     * @param name      the name
     * @param birthdate the birthdate
     * @param startdate the startdate
     */
    @Builder
    public MemberEntity(String username
            , String password
            , String email
            , String phoneNum
            , String name
            , Date birthdate
            , Date startdate
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.name = name;
        this.birthdate = birthdate;
        this.startdate = startdate;

    }


}
