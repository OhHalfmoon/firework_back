package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.AlarmEntity;
import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.BoardEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
 * 2023/06/12        우성준           알림 리스트 출력 수정(상위 5개만) 및 알림 개수 출력 추가
 * 2023/06/13        우성준           내림차순을 PK값을 기준으로 바꿈
 * 2023/06/14        우성준           알림 리스트->슬라이스로 변경
 */
public interface AlarmRepository extends JpaRepository<AlarmEntity, Long> {
//    List<AlarmEntity> findTop5ByAlarmReceiverAndAlarmNoLessThanOrderByAlarmNoDesc(MemberEntity alarmReceiver, Long last);

    Slice<AlarmEntity> findSliceByAlarmReceiver(MemberEntity alarmReceiver, Pageable pageable);

    Long countAlarmEntitiesByAlarmReceiver(MemberEntity alarmReceiver);

    List<AlarmEntity> findAllByApprovalNo(ApprovalEntity approvalEntity);

    List<AlarmEntity> findAllByBoardNo(BoardEntity boardEntity);
}
