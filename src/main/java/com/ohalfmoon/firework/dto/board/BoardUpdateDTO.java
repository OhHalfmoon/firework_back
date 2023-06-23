package com.ohalfmoon.firework.dto.board;

import com.ohalfmoon.firework.model.BoardEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardUpdateDTO {
    private Long boardNo;
    private String boardTitle;
    private String boardContent;

    public BoardUpdateDTO(final BoardEntity entity) {
        boardNo = entity.getBoardNo();
        boardTitle = entity.getBoardTitle();
        boardContent = entity.getBoardContent();
    }

    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .boardNo(boardNo)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .build();
    }
}
