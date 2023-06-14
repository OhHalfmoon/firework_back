package com.ohalfmoon.firework.dto.sub;

import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * packageName    : com.ohalfmoon.firework.dto.sub
 * fileName       : SubLineSaveDTO
 * author         : 이지윤
 * date           : 2023/06/08
 * description    : 결재 선 셍성 시 필요한 정보 DTO
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
public class SubLineSaveDTO {
    private Integer orderLevel;
    private Long lineNo;
    private Long userNo;
    private LocalDateTime regdate;

    public SubLineSaveDTO(final SubLineEntity entity) {
        this.orderLevel = entity.getOrderLevel();
        this.lineNo = entity.getMasterLineEntity().getLineNo();
        this.userNo = entity.getMemberEntity().getUserNo();
        this.regdate = entity.getRegdate();
    }

    public SubLineEntity toEntity(){
        return SubLineEntity.builder()
                .orderLevel(orderLevel)
                .masterLineEntity(MasterLineEntity.builder()
                        .lineNo(lineNo)
                        .build())
                .memberEntity(MemberEntity.builder()
                        .userNo(userNo)
                        .build())
                .build();
    }
}
