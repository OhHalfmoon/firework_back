package com.ohalfmoon.firework.dto.approval;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : ApprovalStateDto
 * author         : 오상현
 * date           : 2023/06/09
 * description    : 결재 최종승인 수정 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/09        오상현           최초 생성
 */
@Getter
@NoArgsConstructor
public class ApprovalStateDto {
    private int approvalState;


    /*
    * 결재진행(db값 : 1) 상태에서 최종결재(db값 : 2)로 상태값을 변경하는 dto
    * */
    @Builder
    public ApprovalStateDto(int approvalState) {
        this.approvalState = approvalState;
    }
}
