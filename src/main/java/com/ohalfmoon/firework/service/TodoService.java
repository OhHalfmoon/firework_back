package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.todo.*;
import com.ohalfmoon.firework.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : TodoService
 * author         : 이지윤
 * date           : 2023/06/19
 * description    : 저장, userNo를 통한 리스트 조회, deptNo를 통한 리스트 조회
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/19        이지윤           최초 생성
 */
@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Long save(TodoSaveDTO dto) {
        return todoRepository.save(dto.toEntity()).getTodoNo();
    }

    public List<TodoListByUserDTO> getListByUserNo(Long userNo) {
        return todoRepository.findByMemberEntity_UserNo(userNo).stream()
                .map(TodoListByUserDTO::new)
                .collect(Collectors.toList());
    }

    public List<TodoListByDeptDTO> getListByDeptNo(Long deptNo) {
        return todoRepository.findByMemberEntity_DeptEntity_DeptNo(deptNo).stream()
                .map(TodoListByDeptDTO::new)
                .collect(Collectors.toList());
    }

    public List<TodoResponseDTO> getList() {
        return todoRepository.findAll().stream()
                .map(TodoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<TodoListByHolidayDTO> getListByHoliday(boolean isHoliday) {
        return todoRepository.findByHoliday(isHoliday).stream()
                .map(TodoListByHolidayDTO::new)
                .collect(Collectors.toList());
    }
}

