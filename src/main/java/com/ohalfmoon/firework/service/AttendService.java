package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.attend.AttendResponseDTO;
import com.ohalfmoon.firework.dto.attend.AttendSaveDTO;
import com.ohalfmoon.firework.dto.attend.AttendUpdateByAdminDTO;
import com.ohalfmoon.firework.dto.attend.AttendUpdateDTO;
import com.ohalfmoon.firework.dto.sub.SubLineSaveDTO;
import com.ohalfmoon.firework.model.AttendEntity;
import com.ohalfmoon.firework.persistence.AttendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
 * 2023/06/19        이지윤           getAttendNo() 추가
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
    public void updateAttend(AttendUpdateDTO dto) {
        AttendEntity entity = attendRepository.findById(dto.getAttendNo())
                .orElseThrow(() -> new IllegalArgumentException("출근 기록이 존재하지 않습니다."));
        entity.update(dto.getLeavedate());
        attendRepository.save(entity);
    }


    public Long getAttendNo(Long userNo) {
        AttendEntity entity = attendRepository.findTopByMemberEntity_UserNoOrderByAttendNoDesc(userNo);
        return entity.getAttendNo();
    }

    public List<AttendResponseDTO> getAttend(Long userNo) {
//        return attendRepository.findTop30ByMemberEntity_UserNoOrderByAttendNoDesc(userNo)
//                .stream().map(AttendResponseDTO::new).findAny().orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다." + userNo));
        return attendRepository.findTop30ByMemberEntity_UserNoOrderByAttendNoDesc(userNo).stream().map(AttendResponseDTO::new).collect(Collectors.toList());
    }
    public void deleteByAdmin(Long attendNo) {
        AttendEntity entity = attendRepository.findById(attendNo)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 출퇴근 기록이 없습니다."));
        attendRepository.delete(entity);
    }

    @Transactional
    public void updateByAdmin(Long attendNo, AttendUpdateByAdminDTO dto) {
        AttendEntity entity = attendRepository.findById(attendNo)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 출퇴근 기록이 없습니다."));
        entity.updateByAdmin(dto.getLeavedate());
    }
}
