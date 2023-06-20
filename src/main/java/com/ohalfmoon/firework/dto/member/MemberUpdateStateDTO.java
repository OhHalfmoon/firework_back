package com.ohalfmoon.firework.dto.member;

import com.ohalfmoon.firework.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName :  com.ohalfmoon.firework.dto.member
 * fileName : MemberUpdateState
 * author :  ycy
 * date : 2023-06-15
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-15                ycy             최초 생성
 */
@NoArgsConstructor
@Data
public class MemberUpdateStateDTO {
    private Long userNo;
    private int state;

    @Builder
    public MemberUpdateStateDTO(Long userNo, int state) {
        this.userNo = userNo;
        this.state = state;
    }

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .userNo(userNo)
                .state(state)
                .build();
    }
}
