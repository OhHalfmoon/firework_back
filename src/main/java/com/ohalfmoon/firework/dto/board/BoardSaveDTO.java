package com.ohalfmoon.firework.dto.board;

import com.ohalfmoon.firework.model.BoardEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import lombok.*;

/**
 * packageName    : com.ohalfmoon.firework.dto.board
 * fileName       : BoardSaveDTO
 * author         : 이지윤
 * date           : 2023/06/22
 * description    : 게시판 추가 시 필요한 정보 DTO
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
public class BoardSaveDTO {
    private Long boardNo;
    private Long userNo;
    private String boardTitle;
    private String boardContent;

    public BoardSaveDTO(final BoardEntity entity) {
        boardNo = entity.getBoardNo();
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
