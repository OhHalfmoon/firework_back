package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.dept.*;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.model.DocboxEntity;
import com.ohalfmoon.firework.persistence.DeptRepository;
import com.ohalfmoon.firework.persistence.DocboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        try{
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
        } catch (Exception e) {
            throw new DataAccessException("부서 추가 중 오류가 발생하였습니다!.", e){};
        }

    }

    @Transactional
    public boolean updateDept(DeptUpdateDto dto){
        try {
            DeptEntity entity = deptRepository.findById(dto.getDeptNo())
                    .orElseThrow(() -> new IllegalArgumentException("해당 부서가 존재하지 않습니다. deptNo = " + dto.getDeptName()));

            entity.update(dto);

            return true;
        }
        catch (Exception e) {
            throw new DataAccessException("부서 업데이트 중 오류가 발생하였습니다!.", e){};
        }
    }

    @Transactional
    public boolean deleteDept(DeptDeleteDto dto){
        try{
            DeptEntity entity = deptRepository.findById(dto.getDeptNo())
                    .orElseThrow(() -> new IllegalArgumentException("해당 부서가 존재하지 않습니다. deptNo = " + dto.getDeptNo()));

            if(entity.getMemberList().size() == 0){
                deptRepository.delete(entity);
                return true;
            }

            return false;
        } catch (Exception e) {
            throw new DataAccessException("부서 삭제 중 오류가 발생하였습니다!.", e){};
        }
    }

}
