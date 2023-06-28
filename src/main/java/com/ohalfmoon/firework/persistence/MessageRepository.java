package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.persistence
 * fileName       : MessageRepository
 * author         : 우성준
 * date           : 2023/06/13
 * description    : 쪽지 Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        우성준           최초 생성
 * 2023/06/16        우성준           페이징 기능 추가
 * 2023/06/23        우성준           JpaSpecificationExecutor 추가 (하지만 검색기능 구현 X)
 */
public interface MessageRepository extends JpaRepository<MessageEntity, Long>, JpaSpecificationExecutor<MessageEntity> {

    // 받은 쪽지 리스트 조회
    Page<MessageEntity> findAllByReceiver(MemberEntity receiver, Pageable pageable);

    // 보낸 쪽지 리스트 조회
    Page<MessageEntity> findAllBySender(MemberEntity sender, Pageable pageable);

    // 미확인 쪽지 개수
    Long countMessageEntitiesByReceiverAndMessageCheckFalse(MemberEntity receiver);
}
