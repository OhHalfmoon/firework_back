package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.board.BoardResponseDTO;
import com.ohalfmoon.firework.dto.board.BoardSaveDTO;
import com.ohalfmoon.firework.dto.board.BoardUpdateDTO;
import com.ohalfmoon.firework.dto.fileUpload.AttachSaveDto;
import com.ohalfmoon.firework.model.BoardEntity;
import com.ohalfmoon.firework.persistence.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : BoardServiceTests
 * author         : 이지윤
 * date           : 2023/06/22
 * description    : 보드 서비스 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/22        이지윤           최초 생성
 * 2023/06/23        이지윤           업데이트 테스트 수정
 * 2023/06/26        이지윤           파일 추가 테스트
 */

@SpringBootTest
@Slf4j
public class BoardServiceTests {
    @Autowired
    private BoardService boardService;

    @Test
    @Rollback(value = false)
    @Transactional
    public void testSave() throws IOException {
        Long userNo = 1L;
        MockMultipartFile file = new MockMultipartFile("uploadFile", "test.txt", "text/plain", "hello file".getBytes());
        BoardSaveDTO boardSaveDTO = BoardSaveDTO.builder()
                .boardTitle("서비스 테스트 제목 4")
                .boardContent("서비스 테스트 내용 4")
                .userNo(userNo)
                .build();
        AttachSaveDto attachSaveDto = AttachSaveDto.builder()
                .uuid(UUID.randomUUID().toString())
                .ext(FilenameUtils.getExtension(file.getOriginalFilename()))
                .originName(file.getOriginalFilename())
                .build();

         Long boardNo = boardService.save(boardSaveDTO, attachSaveDto, file);
         log.info("boardSaveDTO : {}", boardSaveDTO);
         log.info("attachSaveDto : {}", attachSaveDto);
         log.info("boardNo : {}", boardNo);

    }

    @Test
    public void testGetList() {
        log.info("{}", boardService.getList());
    }

    @Test
    public void testGet() {
        Long boardNo = 2L;
        log.info("{}", boardService.get(boardNo));
    }

    @Test
    @Rollback(value = false)
    @Transactional
    public void testDelete() {
        Long boardNo = 2L;
        boardService.delete(boardNo);
    }

    @Test
    @Rollback(value = false)
    @Transactional
    public void testUpdate() {
        Long boardNo = 9L;
        BoardUpdateDTO boardUpdateDTO = BoardUpdateDTO.builder()
                .boardTitle("service test title4")
                .boardContent("service test content4")
                .build();
        boardService.update(boardNo, boardUpdateDTO);
    }

    @Test
    @DisplayName("최근 작성된 게시글 5개 불러오기")
    public void testListTop() {
        boardService.getListTop();
        log.info("boardList : {}", boardService.getListTop());
    }
}
