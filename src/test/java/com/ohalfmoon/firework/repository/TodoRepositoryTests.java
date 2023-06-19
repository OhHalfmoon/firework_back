package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.TodoEntity;
import com.ohalfmoon.firework.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testSave() {
        todoRepository.save(TodoEntity.builder()
                .todoCategory(2L)
                .memberEntity(MemberEntity.builder()
                        .userNo(2L)
                        .build()
                )
                .todoStart(LocalDateTime.now())
                .todoFinish(LocalDateTime.of(2023, 6, 24, 12, 0, 0))
                .todoTitle("todo 테스트 제목5")
                .todoDetail("todo 테스트 내용5")
                .build());
    }

    // todoNo를 통한 단일 조회
    @Test
    public void testFindByTodoNo() {
        Long todoNo = 1L;

        TodoEntity todoEntity = todoRepository.findById(todoNo).orElseThrow(() -> new IllegalArgumentException("todoNo를 통한 조회 실패"));
        log.info("todoEntity: {}", todoEntity);
    }

    // category를 통한 리스트 조회
    @Test
    public void testTodoEntitiesFindByCategory() {
        Long todoCategory = 1L;

//        todoRepository.getTodoEntitiesByTodoCategory(todoCategory).forEach(todoEntity -> {
//            log.info("todoEntity: {}", todoEntity);
//        });
    }

    @Test
    public void testGetList() {
        todoRepository.findAll().forEach(todoEntity -> {
            log.info("todoEntity: {}", todoEntity);
        });
    }
}