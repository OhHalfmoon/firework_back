package com.ohalfmoon.firework.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
@DynamicUpdate
@Builder
@ToString(exclude = "deptEntity")
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
    @Setter
    private DeptEntity deptEntity; // 부서번호

    @ManyToOne // foreign key
    @JoinColumn(name = "positionNo") // foreign key column name
    private PositionEntity positionEntity; // 직급번호

    @Column(nullable = false, name = "roleName")
    @Enumerated(EnumType.ORDINAL)
    private Role roleName;

    @Column(nullable = false)
    private String name; // 이름

    @Column(nullable = false)
    private Boolean manager; // 관리자 여부

    @Column(nullable = true)
    private LocalDate birthdate; // 생일

    private String authProvider; // login 제공자

    @OneToOne
    @JoinColumn(name = "memberSign")
    private AttachEntity attachEntity; // 전자서명 (base64)

    @Column(nullable = false)
    private LocalDate startdate; // 입사일

    private LocalDate enddate; // 퇴사일

    @Column(nullable = false, name = "state")
    @Enumerated(EnumType.ORDINAL)
    private State state; // 가입승인여부

    private LocalDate regdate; // 등록일자

    private LocalDateTime updatedate; // 업데이트 일자

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
    public void update(String email, String phoneNum, String name, LocalDate birthdate, LocalDate startdate) {
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
    public void updateState(Long userNo, State state) {
        this.userNo = userNo;
        this.state = state;
    }

    public void updateAttachEntity(Long attachNo) {
        this.attachEntity = AttachEntity.builder().attachNo(attachNo).build();
    }

    public void updateByAdmin(Role roleName, State state, boolean manager) {
//        this.userNo = userNo;
        this.roleName = roleName;
        this.state = state;
        this.manager = manager;

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
            , LocalDate birthdate
            , LocalDate startdate
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
