package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.sub.SubLineResponseDTO;
import com.ohalfmoon.firework.dto.sub.SubLineSaveDTO;
import com.ohalfmoon.firework.dto.sub.SubLineUpdateDTO;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import com.ohalfmoon.firework.persistence.SubLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : SubLineService
 * author         : 이지윤
 * date           : 2023/06/07
 * description    : Create, Delete
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        이지윤           최초 생성
 */
@Service
public class SubLineService {
    @Autowired
    private SubLineRepository subLineRepository;

    @Transactional
    public Long save(SubLineSaveDTO dto) {
        return subLineRepository.save(dto.toEntity()).getSubLineNo();
    }

    @Transactional
    public Long update(Long subLineNo, SubLineUpdateDTO dto) {
        SubLineEntity entity = subLineRepository.findById(subLineNo)
                .orElseThrow(() -> new IllegalArgumentException("라인이 존재하지 않습니다."));
        entity.update(dto.getOrderLevel(), dto.toEntity().getUserNo());
        subLineRepository.save(entity);
        return subLineNo;
    }

    public SubLineResponseDTO findBySubLineNo(Long subLineNo) {
        SubLineEntity entity = subLineRepository.findById(subLineNo)
                .orElseThrow(()-> new IllegalArgumentException("라인이 존재하지 않습니다."));
        return new SubLineResponseDTO(entity);
    }

    @Transactional
    public void delete(Long subLineNo) {
        SubLineEntity entity = subLineRepository
                .findById(subLineNo)
                .orElseThrow(() -> new IllegalArgumentException("라인이 존재하지 않습니다."));
        subLineRepository.delete(entity);
    }

}
