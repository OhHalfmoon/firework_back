package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.MessageResponseDto;
import com.ohalfmoon.firework.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

/*    @GetMapping({"/member/{userNo}","/member/{userNo}/{messageNo}"})
    public List<MessageResponseDto>*/
}
