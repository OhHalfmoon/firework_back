package com.ohalfmoon.firework.dto.message;

import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import lombok.*;
import org.springframework.data.domain.Page;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : MessagePageDto
 * author         : 우성준
 * date           : 2023/06/15
 * description    : 쪽지리스트와 페이징을 합친 Dto
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

    // 페이지 유틸기능 dto
    private PageResponseDTO pageResponseDTO;

    // 페이징 처리된 쪽지 리스트
    private Page<MessageResponseDto> messageList;

}
