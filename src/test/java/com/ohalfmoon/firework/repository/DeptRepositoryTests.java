package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.dto.dept.DeptCountResponseDTO;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.persistence.DeptRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName :  com.ohalfmoon.firework.repository
 * fileName : DeptRepositoryTests
 * author :  ycy
 * date : 2023-06-09
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-09                ycy             최초 생성
 */
@SpringBootTest
@Slf4j
public class DeptRepositoryTests {

    @Autowired
    DeptRepository deptRepository;

    @Test
    public void deptListTests() {
        List<DeptEntity> deptList = deptRepository.findAll();
        log.info("{}", deptList);

    }

    @Transactional
    @Test
    public void deptListCountTests(){
        Specification<DeptEntity> spec = (root, query, criteriaBuilder) -> {
           Join<Object, Object> memberJoin = root.join("memberList", JoinType.LEFT);
           query.groupBy(memberJoin.get("userNo"));
//           query.distinct(true);

           return null;
        };


//        List<DeptCountResponseDTO> list = deptRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "deptNo")).stream()
//                .map(DeptCountResponseDTO::new)
//                .collect(Collectors.toList());

        List<Tuple> tupleList = deptRepository.findDeptWithCount();

        List<DeptCountResponseDTO> collect = tupleList.stream().map(tuple -> new DeptCountResponseDTO(
                tuple.get(0, Long.class),
                tuple.get(1, String.class),
                tuple.get(2, Long.class).intValue()
        )).collect(Collectors.toList());

        log.info("list : {}", collect);
    }
}
