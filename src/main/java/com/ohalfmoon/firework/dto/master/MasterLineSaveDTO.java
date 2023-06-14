package com.ohalfmoon.firework.dto.master;

import com.ohalfmoon.firework.dto.sub.SubLineSaveDTO;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.dto.master
 * fileName       : MasterLineSaveDTO
 * author         : 이지윤
 * date           : 2023/06/08
 * description    : 결재 선 저장 시 필요한 정보 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/08        이지윤           최초 생성
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MasterLineSaveDTO {
    private String lineName;
    private Long userNo;
    private List<Long> userNos;

    public MasterLineSaveDTO(final MasterLineEntity entity) {
        this.lineName = entity.getLineName();
        this.userNo = entity.getMemberEntity().getUserNo();
    }

    public MasterLineEntity toEntity(){
        return MasterLineEntity.builder()
                .lineName(lineName)
                .memberEntity(MemberEntity.builder()
                        .userNo(userNo)
                        .build())
                .build();
    }
}
