package com.ohalfmoon.firework.model;

import com.ohalfmoon.firework.dto.approval.ApprovalResponseDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : ApprovalEntity
 * author         : 오상현
 * date           : 2023/06/07
 * description    : 결재 Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        오상현            최초 생성
 * 2023/06/08        오상현            response 및 register를 위한 toDto 생성
 * 2023/06/09        오상현            update관련 기능 생성
 */
@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "tbl_approval")
@DynamicInsert
@Builder
@ToString
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

    //approvalOrder : 중간결재 상태 1= 작성완료, 1번 결재자에게 요청 || 2= 1번결재자 결재완료, 2번결재자에게 요청 ...
    @Column(nullable = false)
    private int approvalOrder;

    // approvalState : 결재진행상태 0 = 작성중/임시저장 || 1 = 결재중 || 2 = 결재완료
    @Column(nullable = false)
    private int approvalState;

    private LocalDate regdate; // 등록일자

    private LocalDate updatedate; // 업데이트 일자

    public ApprovalResponseDto toDto() {
        return ApprovalResponseDto.builder()
                .approvalNo(approvalNo)
                .approvalName(approvalName)
                .docboxNo(docboxEntity.getDocboxNo())
                .docboxName(docboxEntity.getDocboxName())
                .approContent(approContent)
                .userNo(memberEntity.getUserNo())
                .name(memberEntity.getName())
                .approvalOrder(approvalOrder)
                .approvalState(approvalState)
                .regdate(regdate)
                .build();
    }

    //작성중인 문서 내용 수정
    public void update(String approvalName, Long lineNo, Long docboxNo, String approContent, int approvalOrder, int approvalState) {
        this.approvalName = approvalName;
        this.masterLineEntity = MasterLineEntity.builder().lineNo(lineNo).build();
        this.docboxEntity = DocboxEntity.builder().docboxNo(docboxNo).build();
        this.approContent = approContent;
        this.approvalOrder = approvalOrder;
        this.approvalState = approvalState;
    }

    // 임시 저장했던 기안을 결재진행 상태로 변경
//    public void updateStorage(int approvalState) {
//        this.approvalState = 1;
//    }

    // 진행중인 결재서류를 결재완료
    public void updateState( int approvalOrder, int approvalState) {
        this.approvalOrder = approvalOrder;
        this.approvalState = approvalState;
    }

    
}
