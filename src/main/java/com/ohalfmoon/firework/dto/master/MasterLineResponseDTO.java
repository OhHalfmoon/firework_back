package com.ohalfmoon.firework.dto.master;


import com.ohalfmoon.firework.dto.sub.SubLineResponseDTO;
import com.ohalfmoon.firework.model.FormEntity;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.dto.master
 * fileName       : MasterLineResponseDTO
 * author         : 이지윤
 * date           : 2023/06/08
 * description    : 결재 선 조회 시 필요한 정보 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/08        이지윤           최초 생성
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MasterLineResponseDTO {
    private Long lineNo;
    private String lineName;
    private Long userNo;
    private String userName;
    @Setter
    private List<SubLineResponseDTO> subLineResponseDTOS;
//    private LocalDateTime regdate;
//    private String name;

    public MasterLineResponseDTO(final MasterLineEntity entity) {
        lineNo = entity.getLineNo();
        lineName = entity.getLineName();
        userNo = entity.getMemberEntity().getUserNo();
//        this.regdate = entity.getRegdate();
        userName = entity.getMemberEntity().getName();
    }

    public MasterLineEntity toEntity(){
        return MasterLineEntity.builder()
                .lineNo(lineNo)
                .build();
    }

}
