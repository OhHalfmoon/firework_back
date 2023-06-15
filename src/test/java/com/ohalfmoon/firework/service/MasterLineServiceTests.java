package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.master.MasterLineResponseDTO;
import com.ohalfmoon.firework.dto.master.MasterLineSaveDTO;
import com.ohalfmoon.firework.dto.master.MasterLineUpdateDTO;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.persistence.MasterLineRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
public class MasterLineServiceTests {
    @Autowired
    private MasterLineService masterLineService;

    @Autowired
    private MasterLineRepository masterLineRepository;

    // 추가 - 성공
    @Test
    @Transactional
    @DisplayName("저장 테스트")
    public void testSave() {
        MasterLineSaveDTO masterLineSaveDTO = MasterLineSaveDTO.builder()
                .lineName("서비스테스트2")
                .userNo(3L)
                .build();

        Long saveLineNo = masterLineService.save(masterLineSaveDTO);
        log.info("saveLineNo : {}", saveLineNo);
        MasterLineEntity masterLineEntity = masterLineRepository.findById(saveLineNo).orElse(null);
        log.info("masterLineEntity : {}", masterLineEntity);
    }

    // 단일 조회 - 성공
    @Test
    @DisplayName("단일 조회 테스트")
    public void testFindByLineNo(){
        Long lineNo = 8L;
        MasterLineResponseDTO masterLineSaveDTO = masterLineService.findByLineNo(lineNo);
        log.info("masterLineSaveDTO : {}", masterLineSaveDTO);
    }

    @Test
    @DisplayName("리스트 조회 테스트")
    public void testgetList() {
        Long userNo = 1L;
        List<MasterLineResponseDTO> masterlineList = masterLineService.getList(userNo);
        log.info("masterlineList : {}", masterlineList);
    }

    // 수정 - 성공
    @Test
    @Transactional
    @DisplayName("수정 테스트")
    public void testUpdate() {
        Long lineNo = 3L;
        MasterLineUpdateDTO updateDTO = MasterLineUpdateDTO.builder()
                .lineName("팀장님 부재 서비스 테스트")
                .build();
        log.info("updateDTO : {}", updateDTO);
        Long updateLineNo = masterLineService.update(lineNo, updateDTO);
        log.info("updateLineNo : {}", updateLineNo);
    }

    // 삭제 - 성공
    @Test
//    @Transactional
    @DisplayName("삭제 테스트")
    public void testDelete(){
        Long lineNo = 20L;
        masterLineService.delete(lineNo);
    }
}
