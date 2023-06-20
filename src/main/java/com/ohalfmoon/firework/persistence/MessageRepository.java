package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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
 * 2023/06/19        우성준           페이징 기능 추가
 */
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    Page<MessageEntity> findAllByReceiver(MemberEntity receiver, Pageable pageable);

    Page<MessageEntity> findAllBySender(MemberEntity sender, Pageable pageable);
    Long countMessageEntitiesByReceiverAndMessageCheckFalse(MemberEntity receiver);
}
