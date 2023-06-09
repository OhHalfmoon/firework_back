package com.ohalfmoon.firework.service;
import com.ohalfmoon.firework.dto.master.MasterLineResponseDTO;
import com.ohalfmoon.firework.dto.master.MasterLineSaveDTO;
import com.ohalfmoon.firework.dto.master.MasterLineUpdateDTO;
import com.ohalfmoon.firework.dto.sub.SubLineResponseDTO;
import com.ohalfmoon.firework.model.FormEntity;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import com.ohalfmoon.firework.persistence.MasterLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : MasterLineService
 * author         : 이지윤
 * date           : 2023/06/07
 * description    : Create, Delete
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        이지윤           최초 생성
 */

@Service
public class MasterLineService {
    @Autowired
    private MasterLineRepository masterLineRepository;

    @Transactional
    public Long save(MasterLineSaveDTO dto) {
        return masterLineRepository.save(dto.toEntity()).getLineNo();
    }

    // lineNo를 통한 단일 조회
    public MasterLineResponseDTO findByLineNo(Long lineNo) {
        MasterLineEntity entity = masterLineRepository.findById(lineNo)
                .orElseThrow(()-> new IllegalArgumentException("라인이 존재하지 않습니다"));
        return new MasterLineResponseDTO(entity);

    }

//    public List<MasterLineResponseDTO> masterLines(Long userNo) {
//        return masterLineRepository.findAllByUserNo(masterLineRepository.findById(userNo));
//    }

    @Transactional
    public Long update(Long lineNo, MasterLineUpdateDTO dto) {
        MasterLineEntity entity = masterLineRepository
                .findById(lineNo)
                .orElseThrow(()-> new IllegalArgumentException("결재 선이 존재하지 않습니다"));
        entity.update(dto.getLineName()); //결재 선 명만 수정 가능
        masterLineRepository.save(entity);
        return lineNo;
    }

    @Transactional
    public void delete(Long lineNo) {
        MasterLineEntity entity = masterLineRepository
                .findById(lineNo)
                .orElseThrow(() -> new IllegalArgumentException("결재 선이 존재하지 않습니다"));
        masterLineRepository.delete(entity);

    }
}
