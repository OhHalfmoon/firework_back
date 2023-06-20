package com.ohalfmoon.firework.dto.member;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName :  com.ohalfmoon.firework.dto.member
 * fileName : MemberUpdateStateListDTO
 * author :  ycy
 * date : 2023-06-19
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-19                ycy             최초 생성
 */

@Data
@NoArgsConstructor
public class MemberUpdateStateListDTO {
    private MemberUpdateStateDTO memberUpdateStateDTO;

    @Builder
    public MemberUpdateStateListDTO(MemberUpdateStateDTO memberUpdateStateDTO) {
        this.memberUpdateStateDTO = memberUpdateStateDTO;
    }
}
