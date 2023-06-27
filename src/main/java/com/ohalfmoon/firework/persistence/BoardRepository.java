package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.persistence
 * fileName       : BoardRepository
 * author         : 이지윤
 * date           : 2023/06/22
 * description    : 저장, 단일 조회, 리스트 조회, 수정, 삭제 기능
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/22        이지윤           최초 생성
 */

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    List<BoardEntity> findTop5ByOrderByBoardNoDesc();

}
