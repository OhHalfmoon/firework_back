package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.dept.DeptCountResponseDTO;
import com.ohalfmoon.firework.dto.dept.DeptListResponseDTO;
import com.ohalfmoon.firework.dto.dept.DeptSaveDto;
import com.ohalfmoon.firework.dto.dept.DeptUpdateDto;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.model.DocboxEntity;
import com.ohalfmoon.firework.persistence.DeptRepository;
import com.ohalfmoon.firework.persistence.DocboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName :  com.ohalfmoon.firework.service
 * fileName : DeptService
 * author :  ycy
 * date : 2023-06-09
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-09                ycy             최초 생성
 * 2023-06-09                ycy            deptList 추가
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DeptService {


    private final DeptRepository deptRepository;

    private final DocboxRepository docboxRepository;

    /**
     * Dept list 조회
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<DeptListResponseDTO> deptList() {
        return deptRepository.findAll().stream()
                .map(DeptListResponseDTO::new) // map을 통해 DeptListResponseDTO로 형변환
                .collect(Collectors.toList()); // List형태로 출력
    }

    public List<DeptCountResponseDTO> deptWithCount() {
        List<Tuple> tupleList = deptRepository.findDeptWithCount();

        // tuple -> dto 전환
        return tupleList.stream().map(tuple -> new DeptCountResponseDTO(
                tuple.get(0, Long.class),
                tuple.get(1, String.class),
                tuple.get(2, Long.class).intValue()
        )).collect(Collectors.toList());
    }

    @Transactional
    public boolean insertDept(DeptSaveDto dto){
            DeptEntity dept = DeptEntity.builder()
                    .deptName(dto.getDeptName())
                    .build();

            deptRepository.save(dept);

            log.info("dto : {}, deptName : {}", dto, dto.getDeptName());

            DocboxEntity box = DocboxEntity.builder()
                    .docboxName(dto.getDeptName() + " 문서함")
                    .build();

            docboxRepository.save(box);

            return true;
    }

    @Transactional
    public boolean updateDept(DeptUpdateDto dto){
        DeptEntity entity = deptRepository.findById(dto.getDeptNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 부서가 존재하지 않습니다. deptNo = " + dto.getDeptName()));

        entity.update(dto);

        return true;
    }

}
