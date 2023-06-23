package com.ohalfmoon.firework.dto.board;

import com.ohalfmoon.firework.model.BoardEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardResponseDTO {
    private Long boardNo;
    private String title;
    private String content;
    private Long userNo;
    private Date regDate;

    public BoardResponseDTO(final BoardEntity boardEntity) {
        boardNo = boardEntity.getBoardNo();
        title = boardEntity.getBoardTitle();
        content = boardEntity.getBoardContent();
        regDate = boardEntity.getRegdate();
    }

    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .boardTitle(title)
                .boardContent(content)
                .build();
    }
}
