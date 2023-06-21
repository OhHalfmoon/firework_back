package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.config.auth.CustomUserDetails;
import com.ohalfmoon.firework.dto.FormResponseDto;
import com.ohalfmoon.firework.dto.approval.*;
import com.ohalfmoon.firework.dto.dept.DeptListResponseDTO;
import com.ohalfmoon.firework.dto.docbox.DocboxListResponseDTO;
import com.ohalfmoon.firework.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
 * 2023/06/12        오상현            update수정, getList 기능 추가
 */
@RequiredArgsConstructor
//@RestController
@Controller
@RequestMapping("/approval")
@Slf4j
public class ApprovalContoller {

    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private FormService formService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private MasterLineService masterLineService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private SubLineService subLineService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private  DocboxService docboxService;

    // 기안 등록
    @PostMapping("/write/{formNo}")
    public String register(@AuthenticationPrincipal CustomUserDetails user, @ModelAttribute ApprovalSaveDto saveDto, Model model) {
        model.addAttribute("user", user);
        log.info("{}",saveDto);
        approvalService.register(saveDto);
        return "redirect:/";
    }

    //상태값 변경 : 임시저장후 기안제출 / 결재완료처리
    @PutMapping("/{approvalNo}")
    @ResponseBody
    public Long updateStorage(@PathVariable Long approvalNo, @RequestBody ApprovalStateDto stateDto) {

        return approvalService.updateState(approvalNo, stateDto);
    }


    //상태값 변경 : 결재반려
    @PutMapping("/reject/{approvalNo}")
    @ResponseBody
    public Long updatereject(@PathVariable Long approvalNo, @RequestBody ApprovalStateDto stateDto) {

        return approvalService.rejectState(approvalNo, stateDto);
    }

    //기안 내용 수정
    @PutMapping("/update/{approvalNo}")
    @ResponseBody
    public Long update(@PathVariable Long approvalNo,  @RequestBody ApprovalUpdateDto updateDto) {
         return approvalService.update(approvalNo, updateDto);

    }



    @GetMapping("/{approvalNo}")
    public String get (@PathVariable Long approvalNo, @AuthenticationPrincipal CustomUserDetails user, Model model) {
        log.info("테스트:{}", approvalNo);
        model.addAttribute("user", user);
        ApprovalResponseDto approvalGet = approvalService.get(approvalNo);
        model.addAttribute("approvalGet", approvalGet);
        List<ApprovalLineDto> approvalLineDto = approvalService.getApprovalUserName(approvalNo);
        model.addAttribute("approUserList", approvalLineDto);
        return "approval/get";
    }

    @GetMapping("/list/{userNo}")
    public List<ApprovalResponseDto> getList(@PathVariable Long userNo) {
        return approvalService.getMyList(userNo);
    }

    @GetMapping("/formList")
    public String getFormList(@AuthenticationPrincipal CustomUserDetails user, Model model) {
        model.addAttribute("user", user);
        List<FormResponseDto> listDto = formService.getFormList();
        model.addAttribute("listDto", listDto);
        return "approval/formList";
    }

    @GetMapping("/write/{formNo}")
    public String write(@PathVariable Long formNo, @AuthenticationPrincipal CustomUserDetails user, Model model) {
        model.addAttribute("user", user);
        model.getAttribute("user");
        log.info("{}","유저정보", user, user.getUserNo());
        model.addAttribute("position", positionService.positionList());
        model.addAttribute("masterList", masterLineService.getList(user.getUserNo()));
        model.addAttribute("masterName", masterLineService.getMasterName(user.getUserNo()));
        //model.addAttribute("subLineList", subLineService.getListByLineNo(user.getUserNo()));
        model.addAttribute("subMemberName", subLineService);
        model.addAttribute("formDetail", formService.findByFormNo(formNo));

        List<DeptListResponseDTO> deptListResponseDTOS = deptService.deptList();
        model.addAttribute("deptList", deptListResponseDTOS);
        List< DocboxListResponseDTO> docboxListResponseDTOS = docboxService.docboxList();
        model.addAttribute("docboxList", docboxListResponseDTOS);
        log.info("{}",formNo);
        return "approval/write";
    }

    @GetMapping("/update/{approvalNo}")
    public String modify(@PathVariable Long approvalNo, @AuthenticationPrincipal CustomUserDetails user, Model model) {
        model.addAttribute("user", user);
        model.getAttribute("user");
        model.addAttribute("position", positionService.positionList());
        model.addAttribute("masterList", masterLineService.getList(user.getUserNo()));
        model.addAttribute("masterName", masterLineService.getMasterName(user.getUserNo()));
        model.addAttribute("subMemberName", subLineService);
        ApprovalResponseDto approvalGet = approvalService.get(approvalNo);
        model.addAttribute("approvalGet", approvalGet);

        List<DeptListResponseDTO> deptListResponseDTOS = deptService.deptList();
        model.addAttribute("deptList", deptListResponseDTOS);
        List< DocboxListResponseDTO> docboxListResponseDTOS = docboxService.docboxList();
        model.addAttribute("docboxList", docboxListResponseDTOS);
        return "approval/update";
    }
}
