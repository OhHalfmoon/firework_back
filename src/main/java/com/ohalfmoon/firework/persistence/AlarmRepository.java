package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.AlarmEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.persistence
 * fileName       : AlarmRepository
 * author         : 우성준
 * date           : 2023/06/07
 * description    : 알람 Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        우성준           최초 생성
 */
public interface AlarmRepository extends JpaRepository<AlarmEntity, Long> {
    List<AlarmEntity> findAllByAlarmReceiver(MemberEntity alarmReceiver);
}
