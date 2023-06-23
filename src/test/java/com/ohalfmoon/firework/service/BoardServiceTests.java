package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.board.BoardSaveDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class BoardServiceTests {
    @Autowired
    private BoardService boardService;

    @Test
    @Rollback(value = false)
    @Transactional
    public void testSave() {
        Long userNo = 1L;
        BoardSaveDTO boardSaveDTO = BoardSaveDTO.builder()
                .boardTitle("service test title3")
                .boardContent("service test content3")
                .userNo(userNo)
                .build();
         Long boardNo = boardService.save(boardSaveDTO);
         log.info("{}", boardNo);
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
}
