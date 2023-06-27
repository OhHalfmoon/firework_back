package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.dto.dept.DeptCountResponseDTO;
import com.ohalfmoon.firework.model.DeptEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNullApi;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

/**
 * packageName :  com.ohalfmoon.firework.persistence
 * fileName : DeptRepository
 * author :  ycy
 * date : 2023-06-07
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-07                ycy             최초 생성
 */
public interface DeptRepository extends JpaRepository<DeptEntity, Long>, JpaSpecificationExecutor<DeptEntity> {
    List<DeptEntity> findAll();

    @Query("select d.deptNo, d.deptName, count(tm.userNo) as count " +
        "from DeptEntity d " +
        "left join MemberEntity tm on d.deptNo = tm.deptEntity.deptNo " +
        "group by d.deptNo, d.deptName")
    List<Tuple> findDeptWithCount();
}
