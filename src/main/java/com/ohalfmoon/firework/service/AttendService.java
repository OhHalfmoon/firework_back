package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.attend.AttendSaveDTO;
import com.ohalfmoon.firework.dto.attend.AttendUpdateDTO;
import com.ohalfmoon.firework.dto.sub.SubLineSaveDTO;
import com.ohalfmoon.firework.model.AttendEntity;
import com.ohalfmoon.firework.persistence.AttendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : AttendService
 * author         : 이지윤
 * date           : 2023/06/15
 * description    : 근태 기록 Service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/15        이지윤           최초 생성
 */
@Service
@Slf4j
public class AttendService {
    @Autowired
    private AttendRepository attendRepository;

    @Autowired
    private MemberService memberService;

    // 출근 등록
    public Long save(AttendSaveDTO dto) {
        return attendRepository.save(dto.toEntity()).getAttendNo();
    }

    // 퇴근 등록 (출근 기록이 존재해야만 등록 가능)
    public void updateAttend(Long attendNo, AttendUpdateDTO dto) {
        AttendEntity entity = attendRepository.findById(attendNo)
                .orElseThrow(() -> new IllegalArgumentException("출근 기록이 존재하지 않습니다."));
        entity.update(dto.getLeavedate());
        attendRepository.save(entity);
    }

}
