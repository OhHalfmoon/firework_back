package com.ohalfmoon.firework.dto.approval;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApprovalStateDto {
    private int approvalState;

    @Builder
    public ApprovalStateDto(int approvalState) {
        this.approvalState = approvalState;
    }
}
