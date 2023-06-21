package com.ohalfmoon.firework.dto.approval;

import com.ohalfmoon.firework.model.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : ApprovalUpdateDto
 * author         : 오상현
 * date           : 2023/06/08
 * description    : 결재 수정 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/08        오상현           최초 생성
 * 2023/06/09        오상현           파라미터 수정 (storage, state값 초기화로 인해 삭제)
 */
@Getter
@NoArgsConstructor
public class ApprovalUpdateDto {
    private String approvalName;
    private Long lineNo;
    private Long docboxNo;
    private String approContent;
    private int approvalOrder;
    private int approvalState;
    private LocalDate regdate;


/*
 * 글 수정
 * 결재상태는 유지시키면서 글의 제목, 내용, 결재선, 문서함만 변경되도록.
 * storage와 state를 파라미터로 받을시 기본값으로 초기화되어 파라미터에서 삭제. 테스트를 통해 현재값 유지 확인
 * */
    @Builder
    public ApprovalUpdateDto(String approvalName, Long lineNo, Long docboxNo, String approContent, int approvalOrder, int approvalState, LocalDate regdate) {
        this.approvalName = approvalName;
        this.lineNo = lineNo;
        this.docboxNo = docboxNo;
        this.approContent = approContent;
        this.approvalOrder = approvalOrder;
        this.approvalState = approvalState;
        this.regdate = regdate;
    }
}
