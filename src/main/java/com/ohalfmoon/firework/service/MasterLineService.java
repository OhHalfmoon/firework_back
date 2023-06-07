package com.ohalfmoon.firework.service;
import com.ohalfmoon.firework.dto.MasterLineDTO;
import com.ohalfmoon.firework.dto.SubLineDTO;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import com.ohalfmoon.firework.persistence.MasterLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Long save(MasterLineDTO dto) {
        return masterLineRepository.save(dto.toEntity()).getLineNo();
    }

    public MasterLineDTO findByMasterLine(Long lineNo) {
        MasterLineEntity entity = masterLineRepository
                .findById(lineNo)
                .orElseThrow(()-> new IllegalArgumentException("결재 선이 존재하지 않습니다"));

        return new MasterLineDTO(entity);
    }

//    public Long update(Long lineNo, MasterLineDTO dto) {
//        MasterLineEntity entity = masterLineRepository
//                .findById(lineNo)
//                .orElseThrow(()-> new IllegalArgumentException("결재 선이 존재하지 않습니다"));
//        entity.update(dto.getLineName()); //결재 선 명만 수정 가능
//        return lineNo;
//    }

    public void delete(MasterLineDTO dto) {
        MasterLineEntity entity = masterLineRepository
                .findById(dto.getLineNo())
                .orElseThrow(() -> new IllegalArgumentException("결재 선이 존재하지 않습니다"));
        masterLineRepository.delete(entity);

    }

}
