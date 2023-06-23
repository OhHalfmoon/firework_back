package com.ohalfmoon.firework.dto.board;

import com.ohalfmoon.firework.model.BoardEntity;
import lombok.*;

/**
 * packageName    : com.ohalfmoon.firework.dto.board
 * fileName       : BoardUpdateDTO
 * author         : 이지윤
 * date           : 2023/06/22
 * description    : 게시판 수정 시 필요한 정보 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/22        이지윤           최초 생성
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardUpdateDTO {
    private String boardTitle;
    private String boardContent;
    private Long userNo;

    public BoardUpdateDTO(final BoardEntity entity) {
        boardTitle = entity.getBoardTitle();
        boardContent = entity.getBoardContent();
        userNo = entity.getMemberEntity().getUserNo();
    }

    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .build();
    }
}
