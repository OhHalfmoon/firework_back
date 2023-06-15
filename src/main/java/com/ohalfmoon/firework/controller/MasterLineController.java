package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.master.MasterLineResponseDTO;
import com.ohalfmoon.firework.dto.master.MasterLineSaveDTO;
import com.ohalfmoon.firework.dto.sub.SubLineResponseDTO;
import com.ohalfmoon.firework.dto.sub.SubLineSaveDTO;
import com.ohalfmoon.firework.service.DeptService;
import com.ohalfmoon.firework.service.MasterLineService;
import com.ohalfmoon.firework.service.MemberService;
import com.ohalfmoon.firework.service.SubLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("line")
@Slf4j
public class MasterLineController {
    @Autowired
    private MasterLineService masterLineService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private SubLineService subLineService;
    @Autowired
    private DeptService deptService;


    @GetMapping("/master")
    public String masterline() {
        return "line/approvalLine";
    }

    @GetMapping("/addLine")
//    @ResponseBody
    public String addline(Model model) {
        // 로그인 한 사용자 regMemberNo가 getList의 parameter로

        List<MasterLineResponseDTO> list = masterLineService.getList(1L);

        log.info("{}", list);
        model.addAttribute("masterList", masterLineService.getList(1L) );

//        model.addAttribute("name", masterLineService.getMasterName(1L));
//        // 특정 lineNo에 해당하는 subLineList를 가져옴
//        model.addAttribute("subLineList", subLineService.getListByLineNo(1L));
//        model.addAttribute("subMemberName", subLineService);
        model.addAttribute("memberList", memberService.getMemberList());
//
//        model.addAttribute("deptList", deptService.deptList());

        return "line/addLine";
    }

    @PostMapping("/addLine")
    public String addLine(
            @ModelAttribute
            MasterLineSaveDTO masterLineSaveDTO,
//            List<SubLineSaveDTO> subLineSaveDTO,
            //String lineName, Long subLine1, Long subLine2, Long subLine3,
            @RequestParam(required = false, defaultValue = "1") Long memberNo) {
        masterLineSaveDTO.setUserNo(memberNo);
//        log.info("lineName : {}", lineName);
//        // 로그인한 사용자
//        MasterLineSaveDTO dto = new MasterLineSaveDTO().builder()
//                .lineName(lineName)
//                .build();
//        SubLineSaveDTO dto1 = new SubLineSaveDTO().builder()
//                .orderLevel(1)
//                .lineNo(1L) //로그인 한 사용자
//                .userNo(subLine1)
//                .build();
//        SubLineSaveDTO dto2 = new SubLineSaveDTO().builder()
//                .orderLevel(2)
//                .lineNo(1L) //로그인 한 사용자
//                .userNo(subLine2)
//                .build();
//        SubLineSaveDTO dto3 = new SubLineSaveDTO().builder()
//                .orderLevel(3)
//                .lineNo(1L) //로그인 한 사용자
//                .userNo(subLine3)
//                .build();

//        masterLineService.save(masterLineSaveDTO);
//        subLineService.save(dto1);
//        subLineService.save(dto2);
//        subLineService.save(dto3);
//        MasterLineSaveDTO dto = masterLineService.save(masterLineSaveDTO);

        masterLineService.save(masterLineSaveDTO);
        log.info("{}", masterLineSaveDTO);


        return "redirect:/line/addLine";
    }

//    @DeleteMapping("/delete/{lineNo}")

    @PostMapping("{lineNo}")
    public String delete(@PathVariable Long lineNo) {
        log.info("lineNo : {}", lineNo);
        masterLineService.delete(lineNo);
        return "redirect:/line/addLine";
    }
}