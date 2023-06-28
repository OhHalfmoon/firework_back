package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.dto.message.MessagePageDto;
import com.ohalfmoon.firework.dto.message.MessageResponseDto;
import com.ohalfmoon.firework.dto.message.MessageSaveDto;
import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import com.ohalfmoon.firework.model.MessageEntity;
import com.ohalfmoon.firework.service.MemberService;
import com.ohalfmoon.firework.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : MessageApiController
 * author         : 우성준
 * date           : 2023/06/14
 * description    : 쪽지 API 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/14        우성준            최초 생성
 * 2023/06/16        우성준            페이징 기능 추가(pagedto 사용)
 * 2023/06/21        우성준            사원검색 추가, 메시지 작성 기능 완료
 * 2023/06/23        우성준            삭제 기능 수정(리스트로 받음)
 * 2023/06/23        우성준            검색 기능 보류
 */

@RequiredArgsConstructor
@RequestMapping("/api/message")
@RestController
@Slf4j
public class MessageApiController {
    private final MessageService messageService;
    private final MemberService memberService;

    // 미확인 쪽지 개수
    @GetMapping("/count/{receiverNo}")
    public Long countMessage(@PathVariable Long receiverNo) {
        return messageService.countMessage(receiverNo);
    }

    // 쪽지 보내기
    @PostMapping("/send")
    public Long save(@RequestBody MessageSaveDto messageSaveDto) {
        return messageService.save(messageSaveDto);
    }

    // 쪽지 수신 확인시 체크
    @PutMapping("/{messageNo}")
    public Long update(@PathVariable Long messageNo, @RequestBody Map<String,  Boolean> map) {
        return messageService.update(messageNo, map.get("messageCheck"));
    }

    // 쪽지 삭제(보낸 사람 받는 사람 다 삭제됩니다)
    public Long delete(@RequestBody List<Long> arrMessage) {
        messageService.deleteAll(arrMessage);
        return arrMessage.size() / 1L;
    }

    // 쪽지 단일 조회
    @GetMapping("/{messageNo}")
    public MessageResponseDto findByMessageNo (@PathVariable Long messageNo) {
        return messageService.findByMessageNo(messageNo);
    }


    // 받은 쪽지 리스트
    @GetMapping("/receiver/{receiverNo}")
    public MessagePageDto findAllByReceiver(@PathVariable  Long receiverNo,
                                            @PageableDefault(
                                               size = 5,
                                               direction = Sort.Direction.DESC,
                                               sort = "messageNo") Pageable pageable) {
       Page<MessageEntity> entities = messageService.messageListByReceiver(receiverNo, pageable);
       return new MessagePageDto(new PageResponseDTO<>(entities), entities.map(MessageResponseDto::new));
    }

    // 보낸 쪽지 리스트
    @GetMapping("/sender/{senderNo}")
    public MessagePageDto findAllBySender(@PathVariable  Long senderNo,
                                       @PageableDefault(
                                               size = 5,
                                               direction = Sort.Direction.DESC,
                                               sort = "messageNo") Pageable pageable) {
        Page<MessageEntity> entities = messageService.messageListBySender(senderNo, pageable);
        return new MessagePageDto(new PageResponseDTO<>(entities), entities.map(MessageResponseDto::new));
    }

    // 쪽지 작성 시 사원검색 기능
    @GetMapping("/sender/{senderNo}/memberList")
    public List<MemberResponseDTO> findAllUser(@PathVariable Long senderNo) {
        return memberService.getAllMemeber(senderNo);
    }
}
