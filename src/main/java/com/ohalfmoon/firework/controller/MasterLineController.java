package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.config.auth.CustomUserDetails;
import com.ohalfmoon.firework.dto.master.MasterLineResponseDTO;
import com.ohalfmoon.firework.dto.master.MasterLineSaveDTO;
import com.ohalfmoon.firework.service.DeptService;
import com.ohalfmoon.firework.service.MasterLineService;
import com.ohalfmoon.firework.service.MemberService;
import com.ohalfmoon.firework.service.SubLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : MasterLineController
 * author         : 이지윤
 * date           : 2023/06/10
 * description    : 결재 라인 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/10        이지윤            최초 생성
 */

@Controller
@RequestMapping("line")
@Slf4j
public class MasterLineController {
    @Autowired
    private MasterLineService masterLineService;
    @Autowired
    private MemberService memberService;
    
    @GetMapping("/master")
    public String masterline() {
        return "line/approvalLine";
    }

    @GetMapping("/addLine")
    public String addline(@AuthenticationPrincipal CustomUserDetails details, Model model) {
        // 로그인 한 사용자
        model.addAttribute("userNo", details);
        List<MasterLineResponseDTO> list = masterLineService.getList(details.getUserNo());
        log.info("{}", list);
        model.addAttribute("masterList", masterLineService.getList(details.getUserNo()) );
        model.addAttribute("memberList", memberService.getMemberList());

        return "line/addLine";
    }

    @PostMapping("/addLine")
    public String addLine(@ModelAttribute MasterLineSaveDTO masterLineSaveDTO,
            @RequestParam(required = false, defaultValue = "1") Long memberNo, @AuthenticationPrincipal CustomUserDetails details) {
        masterLineSaveDTO.setUserNo(details.getUserNo());
        masterLineService.save(masterLineSaveDTO);
        log.info("{}", masterLineSaveDTO);

        return "redirect:/line/addLine";
    }

    @PostMapping("{lineNo}")
    public String delete(@PathVariable Long lineNo) {
        log.info("lineNo : {}", lineNo);
        masterLineService.delete(lineNo);
        return "redirect:/line/addLine";
    }
}
