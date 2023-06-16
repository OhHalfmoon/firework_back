package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.attend.AttendSaveDTO;
import com.ohalfmoon.firework.persistence.AttendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void save(AttendSaveDTO dto) {
        attendRepository.save(dto.toEntity());
    }

    public void saveLeaveWork(Long attendNo, AttendSaveDTO dto) {

        attendRepository.save(dto.toEntity());
    }

}
