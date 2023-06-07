package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.SubLineDTO;
import com.ohalfmoon.firework.model.SubLineEntity;
import com.ohalfmoon.firework.persistence.SubLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Long save(SubLineDTO dto) {
        return subLineRepository.save(dto.toEntity()).getSubLineNo();
    }

    public SubLineDTO findBySubLine(Long subLineNo) {
        SubLineEntity entity = subLineRepository.findById(subLineNo)
                .orElseThrow(() -> new IllegalArgumentException("라인이 존재하지 않습니다."));
        return new SubLineDTO(entity);
    }

    public void delete(SubLineDTO dto) {
        SubLineEntity entity = subLineRepository.findById(dto.getSubLineNo())
                .orElseThrow(() -> new IllegalArgumentException("라인이 존재하지 않습니다."));
        subLineRepository.delete(entity);
    }

}
