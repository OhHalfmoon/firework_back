package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.MessagePageDto;
import com.ohalfmoon.firework.dto.MessageResponseDto;
import com.ohalfmoon.firework.dto.MessageSaveDto;
import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import com.ohalfmoon.firework.model.MessageEntity;
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
import java.util.Optional;
import java.util.stream.Collectors;

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
 */

@RequiredArgsConstructor
@RequestMapping("/api/message")
@RestController
@Slf4j
public class MessageApiController {
    private final MessageService messageService;

    // 받은 쪽지 리스트 출력
//    @GetMapping({"/receiver/{receiverNo}","/receiver/{receiverNo}/{messageNo}"})
//    public List<MessageResponseDto> findListByReceiver(@PathVariable Long receiverNo, @PathVariable(required = false)Optional<Long> messageNo){
//        return messageService.findListByReceiver(receiverNo, messageNo.orElse(null));
//    }

    // 보낸 쪽지 리스트 출력
    @GetMapping({"/sender/{senderNo}","/sender/{senderNo}/{messageNo}"})
    public List<MessageResponseDto> findListBySender(@PathVariable Long senderNo, @PathVariable(required = false)Optional<Long> messageNo){
        return messageService.findListByReceiver(senderNo, messageNo.orElse(0L));
    }

    // 받은 쪽지
    @GetMapping("/count/{receiverNo}")
    public Long countMessage(@PathVariable Long receiverNo) {
        return messageService.countMessage(receiverNo);
    }

    @PostMapping("/")
    public Long save(@RequestBody MessageSaveDto messageSaveDto) {
        return messageService.save(messageSaveDto);
    }

    @PutMapping("/{messageNo}")
    public Long update(@PathVariable Long messageNo, @RequestBody Map<String,  Boolean> map) {
        return messageService.update(messageNo, map.get("messageCheck"));
    }

    @DeleteMapping("/{messageNo}")
    public Long delete(@PathVariable Long messageNo) {
        messageService.delete(messageNo);
        return messageNo;
    }

    @GetMapping("/{messageNo}")
    public MessageResponseDto findByMessageNo (@PathVariable Long messageNo) {
        return messageService.findByMessageNo(messageNo);
    }

//    @GetMapping("/receiver/{receiverNo}")
//    public Page<MessageResponseDto> findByPaging(@PathVariable  Long receiverNo,
//                                                 @PageableDefault(page = 0,
//                                                         size = 5,
//                                                         direction = Sort.Direction.DESC,
//                                                         sort = "messageNo") Pageable pageable) {
//        return messageService.messageListByPaging(receiverNo, pageable);
//    }

    @GetMapping("/receiver/{receiverNo}")
    public MessagePageDto findByPaging(@PathVariable  Long receiverNo,
                                       @PageableDefault(
                                               size = 5,
                                               direction = Sort.Direction.DESC,
                                               sort = "messageNo") Pageable pageable) {
       Page<MessageEntity> entities = messageService.messageListByPaging(receiverNo, pageable);
       return new MessagePageDto(new PageResponseDTO<>(entities), entities.map(MessageResponseDto::new));
    }
}
