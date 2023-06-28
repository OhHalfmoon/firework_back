package com.ohalfmoon.firework.dto.message;

import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.MessageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : MessageSaveDto
 * author         : 우성준
 * date           : 2023/06/13
 * description    : 쪽지등록 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        우성준           최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageSaveDto {

    // 쪽지 받을 사원일련번호
    private Long receiver;

    // 쪽지 보낼 사원일련번호
    private Long sender;

    // 쪽지 제목
    private String messageTitle;

    // 쪽지 내용
    private String messageContent;

    // 엔티티로 변환
    public MessageEntity toEntity(){
        return MessageEntity.builder()
                .receiver(MemberEntity.builder().userNo(receiver).build())
                .sender(MemberEntity.builder().userNo(sender).build())
                .messageTitle(messageTitle)
                .messageContent(messageContent)
                .build();
    }
}
