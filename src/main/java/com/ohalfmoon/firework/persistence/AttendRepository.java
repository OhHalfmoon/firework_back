package com.ohalfmoon.firework.persistence;


import com.ohalfmoon.firework.model.AttendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * packageName    : com.ohalfmoon.firework.persistence
 * fileName       : AttendRepository
 * author         : 이지윤
 * date           : 2023/06/15
 * description    : 근태 Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/15        이지윤           최초 생성
 */
public interface AttendRepository extends JpaRepository<AttendEntity, Long> {
}
