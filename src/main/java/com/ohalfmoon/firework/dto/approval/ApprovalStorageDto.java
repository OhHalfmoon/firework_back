package com.ohalfmoon.firework.dto.approval;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApprovalStorageDto {
    private boolean storage;
    private int approvalState;

    @Builder
    public ApprovalStorageDto(boolean storage, int approvalState) {

        this.storage = storage;
        this.approvalState = approvalState;
    }
}
