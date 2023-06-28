package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.alarm.AlarmResponseDto;
import com.ohalfmoon.firework.dto.alarm.AlarmSaveDto;
import com.ohalfmoon.firework.model.AlarmEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.AlarmRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : AlarmService
 * author         : 우성준
 * date           : 2023/06/08
 * description    : 알림 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/08        우성준           최초 생성
 * 2023/06/12        우성준           알림 리스트 출력 수정(상위 5개만) 및 알림 개수 출력 추가
 * 2023/06/14        우성준           알림 리스트->슬라이스로 변경
 */
@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;

    // 알림 생성
    @Transactional
    public Long save(AlarmSaveDto alarm) { return alarmRepository.save(alarm.toEntity()).getAlarmNo();}

    // 알림 단일 조회
    @Transactional
    public AlarmResponseDto findByAlarmNo(Long alarmNo) {
        AlarmEntity alarm = alarmRepository
                .findById(alarmNo)
                .orElseThrow(()-> new NullPointerException("존재하지 않은 알림입니다"));

        return new AlarmResponseDto(alarm);
    }

    // 알림 확인 업데이트
    @Transactional
    public Long update(Long alarmNo, boolean alarmCheck){
        AlarmEntity alarmEntity = alarmRepository.findById(alarmNo)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않은 알림입니다"));

        alarmEntity.update(alarmCheck);
        return alarmRepository.save(alarmEntity).getAlarmNo();
    }

    // 알림 삭제
    @Transactional
    public void delete(Long alarmNo) {
        AlarmEntity alarmEntity = alarmRepository.findById(alarmNo)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않은 알림입니다"));

        alarmRepository.delete(alarmEntity);
    }

    // 미확인 알림 개수
    @Transactional
    public Long countAlarmByAlarmReceiver(Long userNo) {
        return alarmRepository.countAlarmEntitiesByAlarmReceiver(memberRepository.findById(userNo).orElse(null));
    }

    // 알림 리스트(슬라이스) 조회
    @Transactional
    public Slice<AlarmResponseDto> findListBySlice(Long userNo, Pageable pageable) {
        return alarmRepository.findSliceByAlarmReceiver(MemberEntity.builder().userNo(userNo).build(), pageable)
                .map(AlarmResponseDto::new);
    }
}
