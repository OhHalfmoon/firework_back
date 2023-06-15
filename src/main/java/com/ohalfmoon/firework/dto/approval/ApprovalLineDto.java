package com.ohalfmoon.firework.dto.approval;

import com.ohalfmoon.firework.dto.master.MasterLineResponseDTO;
import com.ohalfmoon.firework.dto.sub.SubLineResponseDTO;
import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ApprovalLineDto {
    private Long approvalNo;
    private Long userNo;
    private String userDept;
    private String userPosition;
    private String userName;

    @Setter
    private List<SubLineResponseDTO> subLineResponseDTOS;

    public ApprovalLineDto(final ApprovalEntity entity) {
        approvalNo = entity.getApprovalNo();
        userNo = entity.getMemberEntity().getUserNo();
        userDept = entity.getMemberEntity().getDeptEntity().getDeptName();
        userPosition = entity.getMemberEntity().getPositionEntity().getPositionName();
        userName = entity.getMemberEntity().getName();


    }


    public ApprovalEntity toLineEntity() {
        return ApprovalEntity.builder()
                .approvalNo(approvalNo)
                .build();
    }

}
