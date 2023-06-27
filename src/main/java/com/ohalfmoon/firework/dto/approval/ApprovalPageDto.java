package com.ohalfmoon.firework.dto.approval;

import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ApprovalPageDto {
    private PageResponseDTO pageResponseDTO;

    private Page<ApprovalResponseDto> approvalResponseDtos;
}
