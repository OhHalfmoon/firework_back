package com.ohalfmoon.firework.dto.board;

import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import lombok.*;
import org.springframework.data.domain.Page;

/**
 * packageName    : com.ohalfmoon.firework.dto.board
 * fileName       : BoardPageDto
 * author         : 이지윤
 * date           : 2023/06/26
 * description    : 게시판 리스트 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/26        이지윤           최초 생성
 */

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class BoardPageDTO {
    private PageResponseDTO pageResponseDTO;

    private Page<BoardResponseDTO> boardList;
}
