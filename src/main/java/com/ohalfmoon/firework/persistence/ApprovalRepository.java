package com.ohalfmoon.firework.persistence;



import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * packageName    : com.ohalfmoon.firework.persistence
 * fileName       : ApprovalRepository
 * author         : 오상현
 * date           : 2023/06/07
 * description    : 결재 Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        오상현            최초 생성
 * 2023/06/12        오상현            결재자 정보 찾아오는 메서드 생성
 * 2023/06/22        오상현            결재상태에 따른 리스트 출력 메서드 생성
 */
public interface ApprovalRepository extends JpaRepository<ApprovalEntity, Long> {
    
    // 기안 이름으로 검색
    ApprovalEntity findByApprovalName(String approvalName);

    // 회원번호를 통해(로그인한 회원번호) 작성된 기안 리스트 전체 출력
    List<ApprovalEntity> findAllByMemberEntity(MemberEntity memberEntity);

    // 로그인한 회원번호와 기안 결재상태값을 통한 리스트 출력
    List<ApprovalEntity> findAllByMemberEntityAndApprovalState(MemberEntity memberEntity, int ApprovalState);

    // 기안번호를 통해 단일 기안 조회 (get)
    ApprovalEntity findByApprovalNo(Long approvalNo);

    // 결재자 정보를 받아옴
    List<ApprovalEntity> findAllByMasterLineEntity(MasterLineEntity masterLineEntity);


}
