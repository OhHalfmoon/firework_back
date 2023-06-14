package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.FormResponseDto;
import com.ohalfmoon.firework.dto.approval.ApprovalResponseDto;
import com.ohalfmoon.firework.dto.approval.ApprovalSaveDto;
import com.ohalfmoon.firework.dto.approval.ApprovalStateDto;
import com.ohalfmoon.firework.dto.approval.ApprovalUpdateDto;
import com.ohalfmoon.firework.dto.dept.DeptListResponseDTO;
import com.ohalfmoon.firework.dto.docbox.DocboxListResponseDTO;
import com.ohalfmoon.firework.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/")
    public Long register( @RequestBody ApprovalSaveDto saveDto) {
        return approvalService.register(saveDto);
    }

    //상태값 변경 : 임시저장후 기안제출 / 결재완료처리
    @PutMapping("/state/{approvalNo}")
    public Long updateStorage(@PathVariable Long approvalNo,  @RequestBody ApprovalStateDto stateDto) {
        return approvalService.updateState(approvalNo, stateDto);
    }

    //기안 내용 수정
    @PutMapping("/{approvalNo}")
    public Long update(@PathVariable Long approvalNo,  @RequestBody ApprovalUpdateDto updateDto) {
        return approvalService.update(approvalNo, updateDto);
    }

    @GetMapping("/{approvalNo}")
    public ApprovalResponseDto get (@PathVariable Long approvalNo) {
        return approvalService.get(approvalNo);
    }

    @GetMapping("/list/{userNo}")
    public List<ApprovalResponseDto> getList(@PathVariable Long userNo) {
        return approvalService.getMyList(userNo);
    }

    @GetMapping("/formList")
    public String getFormList(Model model) {
        List<FormResponseDto> listDto = formService.getFormList();
        model.addAttribute("listDto", listDto);
        return "approval/formList";
    }

    @GetMapping("/write")
    public void write(Model model) {
        model.addAttribute("position", positionService.positionList());
        model.addAttribute("masterList", masterLineService.getList(1L) );
        model.addAttribute("masterName", masterLineService.getMasterName(1L));
        model.addAttribute("subLineList", subLineService.getListByLineNo(1L));
        model.addAttribute("subMemberName", subLineService);

        List<DeptListResponseDTO> deptListResponseDTOS = deptService.deptList();
        model.addAttribute("deptList", deptListResponseDTOS);
        List< DocboxListResponseDTO> docboxListResponseDTOS = docboxService.docboxList();
        model.addAttribute("docboxList", docboxListResponseDTOS);
//        model.addAttribute("member", memberService);
    }
}
