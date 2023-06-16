package com.ohalfmoon.firework.dto.approval;

import com.ohalfmoon.firework.model.ApprovalEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : ApprovalResponseDto
 * author         : 오상현
 * date           : 2023/06/08
 * description    : 결재 조회 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/08        오상현           최초 생성
 */
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class ApprovalResponseDto {
    private Long approvalNo;
    private String approvalName;
    private Long docboxNo;
    private String docboxName;
    private String approContent;
    private Long userNo;
    private String name;
    private int approvalOrder;
    private int approvalState;
    private Date regdate;

    public ApprovalResponseDto(ApprovalEntity approvalEntity) {
        approvalNo = approvalEntity.getApprovalNo();
        approvalName = approvalEntity.getApprovalName();
        docboxNo = approvalEntity.getDocboxEntity().getDocboxNo();
        docboxName = approvalEntity.getDocboxEntity().getDocboxName();
        approContent = approvalEntity.getApproContent();
        userNo = approvalEntity.getMemberEntity().getUserNo();
        name = approvalEntity.getMemberEntity().getName();
        approvalOrder = approvalEntity.getApprovalOrder();
        approvalState = approvalEntity.getApprovalState();
        regdate = approvalEntity.getRegdate();

    }
}
