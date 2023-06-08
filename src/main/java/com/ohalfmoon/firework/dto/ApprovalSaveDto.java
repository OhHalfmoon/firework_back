package com.ohalfmoon.firework.dto;

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
//    private int storage;
//    private int approvalState;

    @Builder
    public ApprovalSaveDto(String approvalName, Long formNo, Long lineNo, Long docboxNo, String approContent, Long userNo, int storage, int approvalState) {
        this.approvalName = approvalName;
        this.formNo = formNo;
        this.lineNo = lineNo;
        this.docboxNo = docboxNo;
        this.approContent = approContent;
        this.userNo = userNo;
//        this.storage = storage;
//        this.approvalState = approvalState;
    }
//    public ApprovalEntity toApproval(FormEntity formEntity
//            , MasterLineEntity masterLineEntity
//            , DocboxEntity docboxEntity
//            , MemberEntity memberEntity
//    ) {
//        return ApprovalEntity.builder()
//                .approvalName(approvalName)
//                .formEntity(formEntity)
//                .masterLineEntity(masterLineEntity)
//                .docboxEntity(docboxEntity)
//                .approContent(approContent)
//                .memberEntity(memberEntity)
//                .build();
//    }
    public ApprovalEntity toApproval() {
        return ApprovalEntity.builder()
                .approvalName(approvalName)
                .formEntity(FormEntity.builder().formNo(formNo).build())
                .masterLineEntity(MasterLineEntity.builder().lineNo(lineNo).build())
                .docboxEntity(DocboxEntity.builder().docboxNo(docboxNo).build())
                .approContent(approContent)
                .memberEntity(MemberEntity.builder().userNo(userNo).build())
                .build();
    }
}
