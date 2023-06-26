package com.ohalfmoon.firework.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateAttachDTO {
    private Long attachNo;

    @Builder
    public MemberUpdateAttachDTO(Long attachNo) {
        this.attachNo = attachNo;
    }
}
