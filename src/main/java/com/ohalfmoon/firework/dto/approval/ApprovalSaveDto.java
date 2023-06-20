package com.ohalfmoon.firework.dto.approval;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ohalfmoon.firework.model.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

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
@Setter
@NoArgsConstructor
@ToString
public class ApprovalSaveDto {
    private String approvalName;
    private Long formNo;
    private Long lineNo;
    private Long docboxNo;
    private String approContent;
    private Long userNo;
    private int approvalOrder; // 중간 결재 진행 상태 : 결재작성완료(제출)시 1 부터 올라감.
    private int approvalState; //결재진행상태 : 0(작성중,임시저장) 1(결재중) 2(결재완료)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private LocalDate regdate;

    @Builder
    public ApprovalSaveDto(String approvalName, Long formNo, Long lineNo, Long docboxNo, String approContent, Long userNo,int approvalOrder, int approvalState, LocalDate regdate) {
        this.approvalName = approvalName;
        this.formNo = formNo;
        this.lineNo = lineNo;
        this.docboxNo = docboxNo;
        this.approContent = approContent;
        this.userNo = userNo;
        this.approvalOrder = approvalOrder;
        this.approvalState = approvalState;
        this.regdate = regdate;
    }
    /*
     * toSaveApproval : 기안 제출
     * 결재진행상태(ApprovalState)를 파라미터로 받아서 0일경우 임시저장, 1일경우 기안제출 처리
     */
    public ApprovalEntity toSaveApproval() {
        return ApprovalEntity.builder()
                .approvalName(approvalName)
                .formEntity(FormEntity.builder().formNo(formNo).build())
                .masterLineEntity(MasterLineEntity.builder().lineNo(lineNo).build())
                .docboxEntity(DocboxEntity.builder().docboxNo(docboxNo).build())
                .approContent(approContent)
                .memberEntity(MemberEntity.builder().userNo(userNo).build())
                .approvalOrder(approvalOrder)
                .approvalState(approvalState)
                .regdate(regdate)
                .build();
    }

    /*
     * toStorageApproval : 최초의 글 임시작성
     * 결재진행상태(ApprovalState)는 기본값  0으로 저장
     * */
//    public ApprovalEntity toStorageApproval() {
//        return ApprovalEntity.builder()
//                .approvalName(approvalName)
//                .formEntity(FormEntity.builder().formNo(formNo).build())
//                .masterLineEntity(MasterLineEntity.builder().lineNo(lineNo).build())
//                .docboxEntity(DocboxEntity.builder().docboxNo(docboxNo).build())
//                .approContent(approContent)
//                .memberEntity(MemberEntity.builder().userNo(userNo).build())
//                .approvalState(0)
//                .build();
//    }
}
