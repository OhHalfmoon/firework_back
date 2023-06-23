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
import org.springframework.http.ResponseEntity;
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

    //상태값 변경 : 결재 중간값 변경
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
    public Long update(@PathVariable Long approvalNo, @RequestBody ApprovalUpdateDto updateDto) {
         return approvalService.update(approvalNo, updateDto);

    }

    // 기안 삭제
    @DeleteMapping("/delete/{approvalNo}")
    @ResponseBody
    public String delete(@PathVariable Long approvalNo ) {
        approvalService.delete(approvalNo);
        return "redirect:/";

    }


    // 기안 단일 조회
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
    //기안 리스트 조회
    @GetMapping("/list/{userNo}")
    public String getList(@AuthenticationPrincipal CustomUserDetails user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("approvalStorage", approvalService.getStateList(user.getUserNo(), 0));
        model.addAttribute("approvalState", approvalService.getStateList(user.getUserNo(), 1));
        model.addAttribute("approvalFinish", approvalService.getStateList(user.getUserNo(), 2));
        model.addAttribute("approvalRequested", approvalService.getSublineUser(user.getUserNo()));
//        model.addAttribute("sublineList", subLineService.getListBySubMember(user.getUserNo()));


        //approvalService.getMyList(user.getUserNo());
        return "approval/approvalList";
    }

//    //결재중인 리스트 조회
//    @GetMapping("/list/state/{userNo}")
//    public List<ApprovalResponseDto> getStateList(@PathVariable Long userNo) {
//        return approvalService.getStateList(userNo);
//    }


    //기안문서양식 선택화면
    @GetMapping("/formList")
    public String getFormList(@AuthenticationPrincipal CustomUserDetails user, Model model) {
        model.addAttribute("user", user);
        List<FormResponseDto> listDto = formService.getFormList();
        model.addAttribute("listDto", listDto);
        return "approval/formList";
    }

    //기안 작성 화면
    @GetMapping("/write/{formNo}")
    public String write(@PathVariable Long formNo, @AuthenticationPrincipal CustomUserDetails user, Model model) {
        model.addAttribute("user", user);
        model.getAttribute("user");
        //log.info("{}","유저정보", user, user.getUserNo());
        model.addAttribute("position", positionService.positionList());
        model.addAttribute("masterList", masterLineService.getList(user.getUserNo()));
        model.addAttribute("masterName", masterLineService.getMasterName(user.getUserNo()));
        //model.addAttribute("subLineList", subLineService.getListByLineNo(user.getUserNo()));
//        model.addAttribute("subMemberName", subLineService);
        model.addAttribute("formDetail", formService.findByFormNo(formNo));

        List<DeptListResponseDTO> deptListResponseDTOS = deptService.deptList();
        model.addAttribute("deptList", deptListResponseDTOS);
        List< DocboxListResponseDTO> docboxListResponseDTOS = docboxService.docboxList();
        model.addAttribute("docboxList", docboxListResponseDTOS);
        log.info("{}",formNo);
        return "approval/write";
    }

    //기안 수정 화면
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
