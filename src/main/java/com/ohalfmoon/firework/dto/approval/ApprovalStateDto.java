package com.ohalfmoon.firework.dto.approval;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : ApprovalStateDto
 * author         : 오상현
em * date           : 2023/06/09
 * description    : 결재 최종승인 수정 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/09        오상현           최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
public class ApprovalStateDto {
    private String approContent;
    private  int approvalOrder;
    private int approvalState;


    /*
    * 결재진행 상태값을 변경하는 dto
    * */
    @Builder
    public ApprovalStateDto(String approContent, int approvalOrder, int approvalState) {
        this.approContent = approContent;
        this.approvalOrder = approvalOrder;
        this.approvalState = approvalState;
    }


}
