package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
 * 2023/06/26        이지윤           JpaSpecificationExecutor 추가, 전체 조회 기능 추가
 */

public interface BoardRepository extends JpaRepository<BoardEntity, Long>, JpaSpecificationExecutor<BoardEntity> {
    Page<BoardEntity> findAll(Pageable pageable);

    List<BoardEntity> findTop5ByOrderByBoardNoDesc();

}
