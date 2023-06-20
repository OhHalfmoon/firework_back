package com.ohalfmoon.firework.dto;

import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import lombok.*;
import org.springframework.data.domain.Page;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : MessagePageDto
 * author         : 우성준
 * date           : 2023/06/15
 * description    : 쪽지리스트 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/15        우성준           최초 생성
 * 2023/06/19        우성준           메시지 리스트 이름수정
 */

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class MessagePageDto {
    private PageResponseDTO pageResponseDTO;

    private Page<MessageResponseDto> messageList;

}
