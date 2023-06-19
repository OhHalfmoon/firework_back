package com.ohalfmoon.firework.persistence;


import com.ohalfmoon.firework.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * packageName    : com.ohalfmoon.firework.persistence
 * fileName       : TodoRepository
 * author         : 이지윤
 * date           : 2023/06/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/19        이지윤           최초 생성
 */
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    // userNo를 통한 리스트 조회
    List<TodoEntity> FindByTodoEntityByMemberEntityUserNo(Long userNo);
}
