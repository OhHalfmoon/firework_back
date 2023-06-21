package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.todo.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : TodoServiceTest
 * author         : 이지윤
 * date           : 2023/06/19
 * description    : Todo Service 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/19        이지윤           최초 생성
 * 2023/06/20        이지윤           리스트 조회 추가
 */

@SpringBootTest
@Slf4j
public class TodoServiceTests {
    @Autowired
    private TodoService todoService;

    @Test
    public void testSave() {
        TodoSaveDTO todoSaveDTO = TodoSaveDTO.builder()
                .isHoliday(true)
                .todoStart(LocalDateTime.now())
                .todoFinish(LocalDateTime.of(2023, 6, 26, 6, 0, 0))
                .todoTitle("테스트 제목")
                .todoDetail("테스트 내용")
                .userNo(5L)
                .build();
        todoService.save(todoSaveDTO);
    }

    @Test
    public void testGetListByUserNo() {
        Long userNo = 1L;
        List<TodoListByUserDTO> dtos = todoService.getListByUserNo(userNo);
        log.info("dtos: {}", dtos);
    }

    @Test
    public void testGetListByDeptNo() {
        Long deptNo = 1L;
        List<TodoListByDeptDTO> dtos = todoService.getListByDeptNo(deptNo);
        log.info("dtos: {}", dtos);
    }

    @Test
    public void testGetList() {
        List<TodoResponseDTO> dtos = todoService.getList();
        log.info("dtos: {}", dtos);
    }

    @Test
    public void testGetListByHoliday() {
        boolean isHoliday = true;
        List<TodoListByHolidayDTO> dtos = todoService.getListByHoliday(isHoliday);
        log.info("dtos: {}", dtos);
    }
}