package com.ohalfmoon.firework.dto.approval;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApprovalStorageDto {
    public boolean storage;

    @Builder
    public ApprovalStorageDto(boolean storage) {
        this.storage = storage;
    }
}
