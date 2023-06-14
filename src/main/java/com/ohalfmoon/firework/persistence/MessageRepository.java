package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.MessageEntity;
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
 */
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findTop5ByReceiverAndMessageNoLessThanOrderByMessageNoDesc(MemberEntity receiver, Long last);

    List<MessageEntity> findTop5BySenderAndMessageNoLessThanOrderByMessageNoDesc(MemberEntity sender, Long last);

    Long countMessageEntitiesByReceiver(MemberEntity receiver);
}
