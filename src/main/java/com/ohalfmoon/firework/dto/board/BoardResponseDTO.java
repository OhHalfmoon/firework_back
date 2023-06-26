package com.ohalfmoon.firework.dto.board;

import com.ohalfmoon.firework.model.BoardEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

/**
 * packageName    : com.ohalfmoon.firework.dto.board
 * fileName       : BoardResponseDTO
 * author         : 이지윤
 * date           : 2023/06/22
 * description    : 게시판 조회 시 필요한 정보 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/22        이지윤           최초 생성
 * 2023/06/23        이지윤           boardNo 추가
 */

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
