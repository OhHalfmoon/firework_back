package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.*;
import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import com.ohalfmoon.firework.model.MemberEntity;
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
 * 2023/06/19        우성준            페이징 기능 추가(pagedto 사용)
 * 2023/06/21        우성준            사원검색 추가
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

    // 받은 쪽지
    @GetMapping("/count/{receiverNo}")
    public Long countMessage(@PathVariable Long receiverNo) {
        return messageService.countMessage(receiverNo);
    }

    @PostMapping("/send")
    public Long save(@RequestBody MessageSaveDto messageSaveDto) {
        return messageService.save(messageSaveDto);
    }

    @PutMapping("/{messageNo}")
    public Long update(@PathVariable Long messageNo, @RequestBody Map<String,  Boolean> map) {
        return messageService.update(messageNo, map.get("messageCheck"));
    }

    @DeleteMapping
    public Long delete(@RequestBody List<Long> arrMessage) {
        messageService.deleteAll(arrMessage);
        return arrMessage.size() / 1L;
    }

    @GetMapping("/{messageNo}")
    public MessageResponseDto findByMessageNo (@PathVariable Long messageNo) {
        return messageService.findByMessageNo(messageNo);
    }


    @GetMapping("/receiver/{receiverNo}")
    public MessagePageDto findAllByReceiver(@PathVariable  Long receiverNo,
                                       @PageableDefault(
                                               size = 5,
                                               direction = Sort.Direction.DESC,
                                               sort = "messageNo") Pageable pageable) {
       Page<MessageEntity> entities = messageService.messageListByReceiver(receiverNo, pageable);
       return new MessagePageDto(new PageResponseDTO<>(entities), entities.map(MessageResponseDto::new));
    }

    @GetMapping("/sender/{senderNo}")
    public MessagePageDto findAllBySender(@PathVariable  Long senderNo,
                                       @PageableDefault(
                                               size = 5,
                                               direction = Sort.Direction.DESC,
                                               sort = "messageNo") Pageable pageable) {
        Page<MessageEntity> entities = messageService.messageListBySender(senderNo, pageable);
        return new MessagePageDto(new PageResponseDTO<>(entities), entities.map(MessageResponseDto::new));
    }

//    @GetMapping("/sender/{senderNo}/memberList")
//    public MemberPageDto findAllUser(@PathVariable Long senderNo,
//                                     @PageableDefault(
//                                             size = 5,
//                                             direction = Sort.Direction.DESC,
//                                             sort = "userNo") Pageable pageable) {
//
//        Page<MemberEntity> entities = memberService.getAllMemberByPaging(senderNo, pageable);
//
//        MemberPageDto memberPageDto = new MemberPageDto(new PageResponseDTO<>(entities), entities.map(MemberResponseDTO::new));
//
//        return memberPageDto;
//    }

//        Page<MemberEntity> entities = memberService.getAllMemeberByPaging(senderNo, pageable);
//        return new MemberPageDto(new PageResponseDTO<>(entities), entities.map(MemberResponseDTO::new));
//    }

    @GetMapping("/sender/{senderNo}/memberList")
    public List<MemberResponseDTO> findAllUser(@PathVariable Long senderNo) {
        return memberService.getAllMemeber(senderNo);
    }

//    @GetMapping("list/{userNo}")
//    public MessagePageDto list(
//            MessagePageRequestDto messagePageRequestDto,
//            @PathVariable Long userNo
//
//    ) {
//        log.info("message dto : {}", messagePageRequestDto);
//
//        Page<MessageEntity> pageDto = messageService.getMessagePages(messagePageRequestDto, messagePageRequestDto.getPageable(Sort.by(Sort.Order.desc("messageNo"))) ,userNo);
//
//        MessagePageDto pageResponse =  new MessagePageDto(new PageResponseDTO<>(pageDto, messagePageRequestDto), pageDto.map(MessageResponseDto::new));
//
//        log.info("pageResponse : {}", pageResponse);
//
//        return pageResponse;
//    }
}
