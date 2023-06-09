package com.ohalfmoon.firework.dto.approval;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : ApprovalStorageDto
 * author         : 오상현
 * date           : 2023/06/09
 * description    : 결재 임시저장상태 수정 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/09        오상현           최초 생성
 */
//@Getter
//@NoArgsConstructor
//public class ApprovalStorageDto {
//    private int approvalState;
//
//    /*
//    * 임시저장한 서류를 결재 진행으로 상태 변경을 할 경우 사용
//    * 최초 문서 작성시, save가 임시저장과 바로 결재진행 두가지의 방향으로 register가 진행되므로
//    * 임시저장을 거치는 문서를 결재로 다시 register가 아닌 상태변경을 위한 dto
//    * approvalState를 0에서 1로 변경
//    * */
//    @Builder
//    public ApprovalStorageDto(int approvalState) {
//        this.approvalState = approvalState;
//    }
//}
