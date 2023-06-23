package com.ohalfmoon.firework.dto.board;

import com.ohalfmoon.firework.model.BoardEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardSaveDTO {
    private Long userNo;
    private String boardTitle;
    private String boardContent;

    public BoardSaveDTO(final BoardEntity entity) {
        boardTitle = entity.getBoardTitle();
        boardContent = entity.getBoardContent();
        userNo = entity.getMemberEntity().getUserNo();
    }

    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .memberEntity(MemberEntity.builder()
                        .userNo(userNo)
                        .build())
                .build();
    }
}
