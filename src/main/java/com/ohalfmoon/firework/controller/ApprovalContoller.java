package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.approval.ApprovalResponseDto;
import com.ohalfmoon.firework.dto.approval.ApprovalSaveDto;
import com.ohalfmoon.firework.dto.approval.ApprovalStateDto;
import com.ohalfmoon.firework.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : ApprovalContoller
 * author         : 오상현
 * date           : 2023/06/09
 * description    : 결재 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/09        오상현            최초 생성
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/approval")
public class ApprovalContoller {
    private final ApprovalService approvalService;

    @PostMapping("/")
    public Long register( @RequestBody ApprovalSaveDto saveDto) {
        return approvalService.register(saveDto);
    }

//    @PutMapping("{approvalNo}")
//    public ApprovalResponseDto updateStorage(@PathVariable Long approvalNo,  @RequestBody ApprovalStateDto stateDto) {
//        return approvalService.updateState(approvalNo, stateDto);
//    }

    @GetMapping("{approvalNo}")
    public ApprovalResponseDto get (@PathVariable Long approvalNo) {
        return approvalService.get(approvalNo);
    }
}