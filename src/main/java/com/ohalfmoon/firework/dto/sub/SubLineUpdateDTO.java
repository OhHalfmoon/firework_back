package com.ohalfmoon.firework.dto.sub;

import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import lombok.*;

/**
 * packageName    : com.ohalfmoon.firework.dto.sub
 * fileName       : SubLineUpdateDTO
 * author         : 이지윤
 * date           : 2023/06/08
 * description    : 결재 선 수정 시 필요한 정보 DTO
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
public class SubLineUpdateDTO {
    private Integer orderLevel;
    private Long userNo;

    public SubLineUpdateDTO(final SubLineEntity entity) {
        this.orderLevel = entity.getOrderLevel();
        this.userNo = entity.getMemberEntity().getUserNo();
    }

    public SubLineEntity toEntity(){
        return SubLineEntity.builder()
                .orderLevel(orderLevel)
                .memberEntity(MemberEntity.builder()
                        .userNo(userNo)
                        .build())
                .build();
    }

}
