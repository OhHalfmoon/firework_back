package com.ohalfmoon.firework.service;

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
    @DisplayName("퇴근 등록(수정) 테스트")
    public void testUpdate() {
        AttendUpdateDTO attendUpdateDTO = AttendUpdateDTO.builder()
                .leavedate(new Date())
                .build();
        attendService.updateAttend(13L, attendUpdateDTO);
    }
}
