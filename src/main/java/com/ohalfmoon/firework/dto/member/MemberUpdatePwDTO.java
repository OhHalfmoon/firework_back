package com.ohalfmoon.firework.dto.member;

import com.ohalfmoon.firework.model.MemberEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * packageName :  com.ohalfmoon.firework.dto.member
 * fileName : MemberUpdatePw
 * author :  ycy
 * date : 2023-06-08
 * description : 변경된 회원의 pw를 매핑하여 entity로 전달
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-08                ycy             최초 생성
 */
@NoArgsConstructor
@Data
public class MemberUpdatePwDTO {
    private String oldPw;
    private String password;

    @Builder
    public MemberUpdatePwDTO(String password) {
        this.password = password;
    }

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .password(password)
                .updatedate(LocalDateTime.now())
                .build();
    }
}
