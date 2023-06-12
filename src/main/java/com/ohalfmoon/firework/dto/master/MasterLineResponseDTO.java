package com.ohalfmoon.firework.dto.master;


import com.ohalfmoon.firework.model.FormEntity;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime regdate;

    public MasterLineResponseDTO(final MasterLineEntity entity) {
        this.lineNo = entity.getLineNo();
        this.lineName = entity.getLineName();
        this.userNo = entity.getMemberEntity().getUserNo();
        this.regdate = entity.getRegdate();
    }

    public MasterLineEntity toEntity(){
        return MasterLineEntity.builder()
                .lineNo(lineNo)
                .build();
    }

}
