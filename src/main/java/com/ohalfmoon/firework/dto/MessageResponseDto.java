package com.ohalfmoon.firework.dto;

import com.ohalfmoon.firework.model.MessageEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : MessageResponseDto
 * author         : 우성준
 * date           : 2023/06/13
 * description    : 쪽지조회 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        우성준           최초 생성
 */

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MessageResponseDto {
    private Long messageNo;

    private Long receiverNo;

    private Long senderNo;

    private String receiverName;

    private String senderName;

    private boolean messageCheck;

    private String messageTitle;

    private String messageContent;

    private LocalDateTime regdate;

    public MessageResponseDto(MessageEntity entity) {
        messageNo = entity.getMessageNo();
        receiverNo = entity.getReceiver().getUserNo();
        senderNo = entity.getSender().getUserNo();
        receiverName = entity.getReceiver().getName();
        senderName = entity.getSender().getName();
        messageCheck = entity.isMessageCheck();
        messageTitle = entity.getMessageTitle();
        messageContent = entity.getMessageContent();
        regdate = entity.getRegdate();
    }
}
