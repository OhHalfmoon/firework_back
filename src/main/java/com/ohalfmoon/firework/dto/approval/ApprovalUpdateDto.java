package com.ohalfmoon.firework.dto.approval;

import com.ohalfmoon.firework.model.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApprovalUpdateDto {
    private String approvalName;
    private Long lineNo;
    private Long docboxNo;
    private String approContent;
//    private boolean storage;
//    private int approvalState;

/*
 * 글 수정
 * 결재상태는 유지시키면서 글의 제목, 내용, 결재선, 문서함만 변경되도록.
 * */
    @Builder
    public ApprovalUpdateDto(String approvalName, Long lineNo, Long docboxNo, String approContent
//            , boolean storage, int approvalState
    ) {
        this.approvalName = approvalName;
        this.lineNo = lineNo;
        this.docboxNo = docboxNo;
        this.approContent = approContent;
//        this.storage = storage;
//        this.approvalState = approvalState;
    }

    /*
     * toUpdateApproval : 최종승인
     * 결재 최종 승인
     * */
//    public ApprovalEntity toFinishApproval() {
//        return ApprovalEntity.builder()
////                .approvalName(approvalName)
////                .masterLineEntity(MasterLineEntity.builder().lineNo(lineNo).build())
////                .docboxEntity(DocboxEntity.builder().docboxNo(docboxNo).build())
////                .approContent(approContent)
//                .storage(false)
//                .approvalState(2)
//                .build();
//    }
}
