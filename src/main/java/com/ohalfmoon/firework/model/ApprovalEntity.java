package com.ohalfmoon.firework.model;

import com.ohalfmoon.firework.dto.approval.ApprovalResponseDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
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
public class ApprovalEntity extends BaseTimeEntity{
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

    // storage : 임시저장 true = 임시저장
    @Column(nullable = false)
    private boolean storage;

    // approvalState : 결재진행상태 0 = 작성중 || 1 = 결재중 || 2 = 결재완료
    @Column(nullable = false)
    private int approvalState;

    public ApprovalResponseDto toDto() {
        return ApprovalResponseDto.builder()
                .approvalNo(approvalNo)
                .approvalName(approvalName)
                .docboxNo(docboxEntity.getDocboxNo())
                .docboxName(docboxEntity.getDocboxName())
                .approContent(approContent)
                .userNo(memberEntity.getUserNo())
                .name(memberEntity.getName())
                .storage(storage)
                .approvalState(approvalState)
                .regdate(getRegdate())
                .build();
    }

    //작성중인 문서 내용 수정
    public void update(String approvalName, Long lineNo, Long docboxNo, String approContent
//            , boolean storage,int approvalState
    ) {
        this.approvalName = approvalName;
        this.masterLineEntity = MasterLineEntity.builder().lineNo(lineNo).build();
        this.docboxEntity = DocboxEntity.builder().docboxNo(docboxNo).build();
        this.approContent = approContent;
//        this.storage = storage;
//        this.approvalState = approvalState;
    }

    // 임시 저장했던 기안을 결재진행 상태로 변경
    public void updateStorage(boolean storage, int approvalState) {
        this.storage = false;
        this.approvalState = 1;
    }

    // 진행중인 결재서류를 결재완료
    public void updateState(int approvalState) {
        this.approvalState = 2;
    }
}
