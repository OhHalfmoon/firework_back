package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.SubLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.ohalfmoon.firework.persistence
 * fileName       : SubLineRepository
 * author         : 이지윤
 * date           : 2023/06/07
 * description    : 기본적인 CRUD 작업
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        이지윤           최초 생성
 */
@Repository
public interface SubLineRepository extends JpaRepository<SubLineEntity, Long> {
}
