package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.AlarmResponseDto;
import com.ohalfmoon.firework.dto.AlarmSaveDto;
import com.ohalfmoon.firework.model.AlarmEntity;
import com.ohalfmoon.firework.persistence.AlarmRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
 */
@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(AlarmSaveDto alarm) { return alarmRepository.save(alarm.toEntity()).getAlarmNo();}

    @Transactional
    public AlarmResponseDto findByAlarmNo(Long alarmNo) {
        AlarmEntity alarm = alarmRepository
                .findById(alarmNo)
                .orElseThrow(()-> new NullPointerException("존재하지 않은 알림입니다"));

        return new AlarmResponseDto(alarm);
    }

    @Transactional
    public List<AlarmResponseDto> findAllByAlarmReceiver(Long userNo) {
       return alarmRepository
                .findAllByAlarmReceiver(memberRepository.findById(userNo).orElse(null))
                        .stream().map(AlarmResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long alarmNo, boolean alarmCheck){
        AlarmEntity alarmEntity = alarmRepository.findById(alarmNo)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않은 알림입니다"));

        alarmEntity.update(alarmCheck);
        return alarmRepository.save(alarmEntity).getAlarmNo();
    }

    @Transactional
    public void delete(Long alarmNo) {
        AlarmEntity alarmEntity = alarmRepository.findById(alarmNo)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않은 알림입니다"));

        alarmRepository.delete(alarmEntity);
    }
}
