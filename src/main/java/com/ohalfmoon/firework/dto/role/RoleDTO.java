package com.ohalfmoon.firework.dto.role;

import com.ohalfmoon.firework.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * packageName :  com.ohalfmoon.firework.dto.form
 * fileName : RoleDTO
 * author :  ycy
 * date : 2023-06-07
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-07                ycy             최초 생성
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {
    private Long userNo;
    private String authName;
    private Date regdate;
    private Date updatedate;

    public RoleEntity toEntity() {
        return RoleEntity.builder()
                .authName("GUEST")
                .build();
    }
}
