package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.BoardEntity;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testSave() {
         BoardEntity boardEntity = BoardEntity.builder()
                 .boardCategory(1)
                 .memberEntity(MemberEntity.builder().userNo(1L).build())
                 .boardTitle("test title2")
                 .boardContent("test content2")
                 .boardCount(0L)
                 .build();
         boardRepository.save(boardEntity);
    }

    @Test
    public void testFindByBoardNo() {
        BoardEntity boardEntity = boardRepository.findById(1L).get();
        log.info("{}", boardEntity);
    }

    @Test
    public void testGetList() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        log.info("{}", boardEntityList);
    }

    @Test
    @Rollback(value = false)
    @Transactional
    public void testUpdate() {
        BoardEntity boardEntity = boardRepository.findById(2L).orElseThrow(()-> new RuntimeException("없음"));
        boardEntity.update("update title", "update content");
        log.info("{}", boardEntity);
    }

    @Test
    public void testDelete() {
        boardRepository.deleteById(1L);
    }
}
