package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.master.MasterLineResponseDTO;
import com.ohalfmoon.firework.dto.master.MasterLineSaveDTO;
import com.ohalfmoon.firework.dto.master.MasterLineUpdateDTO;
import com.ohalfmoon.firework.dto.sub.SubLineResponseDTO;
import com.ohalfmoon.firework.dto.sub.SubLineSaveDTO;
import com.ohalfmoon.firework.dto.sub.SubLineUpdateDTO;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import com.ohalfmoon.firework.persistence.SubLineRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
public class SubLineServiceTests {
    @Autowired
    private SubLineRepository subLineRepository;

    @Autowired
    private SubLineService subLineService;

    // 추가 - 성공
    @Test
    @Transactional
    @DisplayName("저장 테스트")
    public void testSave(){
        SubLineSaveDTO subLineSaveDTO = SubLineSaveDTO.builder()
                .orderLevel(3)
                .lineNo(1L)
                .userNo(4L)
                .build();

        Long saveSubLineNo = subLineService.save(subLineSaveDTO);
        log.info("saveSubLineNo: {}", saveSubLineNo);
        SubLineEntity subLineEntity = subLineRepository.findById(saveSubLineNo).orElse(null);
        log.info("subLineEntity: {}", subLineEntity);
    }


    @Test
    @DisplayName("단일 조회 테스트")
    public void testFindBySubLineNo() {
        Long subLineNo = 5L;
        SubLineResponseDTO subLineResponseDTO = subLineService.findBySubLineNo(subLineNo);
        log.info("subLineResponseDTO: {}", subLineResponseDTO);
    }

    @Test
    @DisplayName("리스트 조회 테스트")
    public void testGetList() {
        List<SubLineResponseDTO> subLineSaveDTO = subLineService.getList();
        log.info("subLineSaveDTO : {}", subLineSaveDTO);
    }


    // 수정 - 성공
    @Test
    @Transactional
    @DisplayName("수정 테스트")
    public void testUpdate() {
        Long subLineNo = 4L;
        SubLineUpdateDTO updateDTO = SubLineUpdateDTO.builder()
                .orderLevel(2)
                .userNo(2L)
                .build();
        log.info("updateDTO: {}", updateDTO);
        Long updateSubLineNo = subLineService.update(subLineNo, updateDTO);
        log.info("updateSubLineNo: {}", updateSubLineNo);
    }

    // 삭제 - 성공
    @Test
    @Transactional
    public void testDelete() {
        Long subLineNo = 4L;
        subLineService.delete(subLineNo);
    }

}
