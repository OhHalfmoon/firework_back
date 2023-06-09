package com.ohalfmoon.firework.dto.approval;

import com.ohalfmoon.firework.model.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : ApprovalSaveDto
 * author         : 오상현
 * date           : 2023/06/08
 * description    : 결재 저장 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/08        오상현           최초 생성
 */
@Getter
@NoArgsConstructor
public class ApprovalSaveDto {
    private String approvalName;
    private Long formNo;
    private Long lineNo;
    private Long docboxNo;
    private String approContent;
    private Long userNo;
    private boolean storage; //임시저장 : false(제출) true(임시저장)
    private int approvalState; //결재진행상태 : 0(작성중) 1(결재중) 2(결재완료)

    @Builder
    public ApprovalSaveDto(String approvalName, Long formNo, Long lineNo, Long docboxNo, String approContent, Long userNo, boolean storage, int approvalState) {
        this.approvalName = approvalName;
        this.formNo = formNo;
        this.lineNo = lineNo;
        this.docboxNo = docboxNo;
        this.approContent = approContent;
        this.userNo = userNo;
        this.storage = storage;
        this.approvalState = approvalState;
    }
    /*
    * toStorageApproval : 최초의 글 임시작성
    * 임시저장(storage)를 true로, 결재진행상태(ApprovalState)는 기본값  0으로 저장
    * */
    public ApprovalEntity toStorageApproval() {
        return ApprovalEntity.builder()
                .approvalName(approvalName)
                .formEntity(FormEntity.builder().formNo(formNo).build())
                .masterLineEntity(MasterLineEntity.builder().lineNo(lineNo).build())
                .docboxEntity(DocboxEntity.builder().docboxNo(docboxNo).build())
                .approContent(approContent)
                .memberEntity(MemberEntity.builder().userNo(userNo).build())
                .storage(true)
                .approvalState(0)
                .build();
    }
    /*
     * toSaveApproval : 기안 제출
     * 임시저장(storage)을 false로 되돌리고, 결재진행상태(ApprovalState)를 1로 변경
     */
    public ApprovalEntity toSaveApproval() {
        return ApprovalEntity.builder()
                .approvalName(approvalName)
                .formEntity(FormEntity.builder().formNo(formNo).build())
                .masterLineEntity(MasterLineEntity.builder().lineNo(lineNo).build())
                .docboxEntity(DocboxEntity.builder().docboxNo(docboxNo).build())
                .approContent(approContent)
                .memberEntity(MemberEntity.builder().userNo(userNo).build())
                .storage(false)
                .approvalState(1)
                .build();
    }
}
