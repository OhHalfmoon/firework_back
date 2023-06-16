package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.attend.AttendSaveDTO;
import com.ohalfmoon.firework.dto.attend.AttendSaveLeaveWorkDTO;
import com.ohalfmoon.firework.model.AttendEntity;
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

    @Test
    @DisplayName("출근 등록 테스트")
    public void testSave() {
        AttendEntity attendEntity = attendRepository.save(AttendSaveDTO.builder()
                .godate(new Date())
                .build().toEntity());
    }
    @Test
    @DisplayName("퇴근 등록(수정) 테스트")
    public void testUpdate() {
        AttendEntity attendEntity = AttendSaveLeaveWorkDTO.builder()
                .leavedate(new Date())
                .build().toEntity();
    }
}
