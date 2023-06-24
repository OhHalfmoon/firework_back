package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.attend.AttendResponseDTO;
import com.ohalfmoon.firework.dto.attend.AttendSaveDTO;
import com.ohalfmoon.firework.dto.attend.AttendUpdateDTO;
import com.ohalfmoon.firework.dto.sub.SubLineSaveDTO;
import com.ohalfmoon.firework.model.AttendEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import com.ohalfmoon.firework.persistence.AttendRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : AttendServiceTests
 * author         : 이지윤
 * date           : 2023/06/15
 * description    : Attend 서비스 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/15        이지윤           최초 생성
 * 2023/06/19        이지윤           출근 번호 조회 테스트 추가
 */

@SpringBootTest
@Slf4j
public class AttendServiceTests {
    @Autowired
    private AttendRepository attendRepository;

    @Autowired
    private AttendService attendService;

    @Test
    @DisplayName("출근 등록 테스트")
    public void testSave() {
        AttendSaveDTO attendSaveDTO = AttendSaveDTO.builder()
                .userNo(1L)
                .godate(new Date())
                .build();
        attendService.save(attendSaveDTO);
    }
    @Test
    @DisplayName("출근 반복 입력 테스트")
    public void testSave2() {
        AttendSaveDTO attendSaveDTO = AttendSaveDTO.builder()
                .userNo(19L)
                .godate(new Date())
                .build();
        for(int i=0; i<30; i++) {
            attendService.save(attendSaveDTO);
        }
    }

    @Test
    @DisplayName("퇴근 등록(수정) 테스트")
    public void testUpdate() {
        AttendUpdateDTO attendUpdateDTO = AttendUpdateDTO.builder()
                .leavedate(new Date())
                .build();
        attendService.updateAttend(attendUpdateDTO);
    }

    @Test
    @DisplayName("출근 번호 조회 테스트")
    public void testGetAttendNo() {
        AttendResponseDTO attendResponseDTO = AttendResponseDTO.builder()
                .attendNo(1L)
                .build();
        attendService.getAttendNo(attendResponseDTO.getAttendNo());
        log.info("attendNo: " + attendResponseDTO.getAttendNo());
    }

    @Test
    @DisplayName("userNo로 조회 테스트")
    public void attendGetTest() {
        AttendResponseDTO dto = new AttendResponseDTO();
        dto.setUserNo(19L);
        log.info("회원 : {}", attendService.getAttend(19L));
    }
}
