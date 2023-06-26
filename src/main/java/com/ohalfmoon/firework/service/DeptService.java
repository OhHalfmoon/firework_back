package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.dept.DeptCountResponseDTO;
import com.ohalfmoon.firework.dto.dept.DeptListResponseDTO;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.persistence.DeptRepository;
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
public class DeptService {

    @Autowired
    private DeptRepository deptRepository;

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


}
