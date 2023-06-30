package com.ohalfmoon.firework.dto.member;

import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import com.ohalfmoon.firework.model.MemberEntity;
import lombok.*;
import org.springframework.data.domain.Page;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : MemberPageDto
 * author         : 우성준
 * date           : 2023/06/20
 * description    : 회원리스트 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/20        우성준           최초 생성
 */

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class MemberPageDto {
    private PageResponseDTO<MemberEntity> pageResponseDTO;
    private Page<MemberResponseDTO> memberList;
}
