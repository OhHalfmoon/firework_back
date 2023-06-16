package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.persistence
 * fileName       : SubLineRepository
 * author         : 이지윤
 * date           : 2023/06/07
 * description    : 기본적인 CRUD 작업, 리스트 조회
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        이지윤           최초 생성
 * 2023/06/11        이지윤           리스트 조회 수정
 */
@Repository
public interface SubLineRepository extends JpaRepository<SubLineEntity, Long> {
//    List<SubLineEntity> findAllByLineNo(MasterLineEntity lineNo);
//    List<SubLineEntity> findAllByLineNo();

     // using LineNo to find SubLine
     List<SubLineEntity> findAllByMasterLineEntity_LineNo(Long lineNo);

     void deleteAllByMasterLineEntity_LineNo(Long lineNo);
}
