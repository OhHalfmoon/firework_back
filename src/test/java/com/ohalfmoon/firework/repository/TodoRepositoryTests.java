package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.TodoEntity;
import com.ohalfmoon.firework.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * packageName    : com.ohalfmoon.firework.repository
 * fileName       : TodoRepositoryTests
 * author         : 이지윤
 * date           : 2023/06/19
 * description    : Todo 레파지토리 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/19        이지윤           최초 생성
 * 2023/06/20        이지윤           리스트 조회 테스트
 */

@SpringBootTest
@Slf4j
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testSave() {
        todoRepository.save(TodoEntity.builder()
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

    @Test
    public void testFindByTodoEntityByMemberEntityUserNo(){
        Long userNo = 2L;

        todoRepository.findByMemberEntity_UserNo(userNo).forEach(todoEntity -> {
            log.info("todoEntity: {}", todoEntity);
        });
    }

    @Test
    public void testFindByMemberEntity_DeptEntity_DeptNo() {
        Long deptNo = 3L;

        todoRepository.findByMemberEntity_DeptEntity_DeptNo(deptNo).forEach(todoEntity -> {
            log.info("todoEntity: {}", todoEntity);
        });
    }

    @Test
    public void testFindByHoliday() {
        todoRepository.findByHoliday(true).forEach(todoEntity -> {
            log.info("todoEntity: {}", todoEntity);
        });
    }

    @Test
    public void testGetList() {
        todoRepository.findAll().forEach(todoEntity -> {
            log.info("todoEntity: {}", todoEntity);
        });
    }
}
