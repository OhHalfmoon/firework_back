package com.ohalfmoon.firework.dto.role;

import com.ohalfmoon.firework.model.Role;
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
 * 2023-06-13             방한솔             dto 필드 & 엔티티 변경 메서드 수정
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {
    private Long roleNo;
    private String roleName;

    /**
     * dto -> 엔티티 변경 메서드
     *
     * @return RoleEntity 권한 엔티티
     */
    public RoleEntity toEntity() {
        return RoleEntity.builder()
                .roleName(Role.ROLE_EMPLOYEE)
                .build();
    }
}
