package com.ohalfmoon.firework.dto.member;

import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.Role;
import com.ohalfmoon.firework.model.State;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName :  com.ohalfmoon.firework.dto.member
 * fileName : MemberUpdateByAdminRequestDTO
 * author :  ycy
 * date : 2023-06-22
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-22                ycy             최초 생성
 */
@Data
@NoArgsConstructor
public class MemberUpdateByAdminRequestDTO {
    private Long userNo;
    private String name;
    private State state;
    private Role roleName;
    private Long deptNo;
    private Long positionNo;
    private boolean manager;

    @Builder
    public MemberUpdateByAdminRequestDTO(Long userNo, String name, State state, Role roleName, Long deptNo, Long positionNo, boolean manager) {
        this.userNo = userNo;
        this.name = name;
        this.state = state;
        this.roleName = roleName;
        this.deptNo = deptNo;
        this.positionNo = positionNo;
        this.manager = manager;
    }
    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .name(name)
                .state(state)
                .roleName(roleName)
                .manager(isManager())
                .build();
    }

}
