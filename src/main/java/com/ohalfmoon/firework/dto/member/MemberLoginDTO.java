package com.ohalfmoon.firework.dto.member;

import com.ohalfmoon.firework.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * packageName :  com.ohalfmoon.firework.dto.member
 * fileName : MemberLoginDTO
 * author :  ycy
 * date : 2023-06-09
 * description : view에서 받은 id, pw를 매핑하여 entity로 전달
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-09                ycy             최초 생성
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginDTO {
    @NotBlank(message = "id를 확인해주세요")
    private String username;
    @NotBlank(message = "pw를 확인해주세요")
    private String password;
}

