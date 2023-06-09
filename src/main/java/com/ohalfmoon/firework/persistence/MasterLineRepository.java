package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.persistence
 * fileName       : MasterLineRepository
 * author         : 이지윤
 * date           : 2023/06/07
 * description    : 기본적인 CRUD 작업
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        이지윤           최초 생성
 */

@Repository
public interface MasterLineRepository extends JpaRepository<MasterLineEntity, Long> {
    //List<MasterLineEntity> findAllByUserNo(MemberEntity userNo);

    List<MasterLineEntity> findByRegMemberNo(Long userNo);

}
