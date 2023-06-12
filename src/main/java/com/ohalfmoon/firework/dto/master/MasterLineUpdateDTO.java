package com.ohalfmoon.firework.dto.master;

import com.ohalfmoon.firework.model.MasterLineEntity;
import lombok.*;

/**
 * packageName    : com.ohalfmoon.firework.dto.master
 * fileName       : MasterLineUpdateDTO
 * author         : 이지윤
 * date           : 2023/06/08
 * description    : 결재 선 수정 시 필요한 정보(기안자, 결재선 명)DTO
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
public class MasterLineUpdateDTO {
    private String lineName;
    private Long userNo;

    public MasterLineUpdateDTO(final MasterLineEntity entity) {
        this.lineName = entity.getLineName();
    }

    public MasterLineEntity toEntity(){
        return MasterLineEntity.builder()
                .lineName(lineName)
                .build();
    }
}

