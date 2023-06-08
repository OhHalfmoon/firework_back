package com.ohalfmoon.firework.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApprovalUpdateDto {
    public int approvalState;

    @Builder
    public ApprovalUpdateDto(int approvalState) {this.approvalState = approvalState;}
}
