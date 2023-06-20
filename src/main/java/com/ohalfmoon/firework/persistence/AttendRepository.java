package com.ohalfmoon.firework.persistence;


import com.ohalfmoon.firework.model.AttendEntity;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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
 * 2023/06/19        이지윤           근태 조회
 */
public interface AttendRepository extends JpaRepository<AttendEntity, Long> {
    AttendEntity findTopByMemberEntity_UserNoOrderByAttendNoDesc(Long userNo);

}
