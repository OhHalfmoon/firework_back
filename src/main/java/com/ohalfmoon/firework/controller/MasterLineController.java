package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.service.MasterLineService;
import com.ohalfmoon.firework.service.SubLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("line")
@Slf4j
public class MasterLineController {
    @Autowired
    private MasterLineService masterLineService;

    @Autowired
    private SubLineService subLineService;

    @GetMapping("/master")
    public String masterline(Model model) {
        model.addAttribute("master", masterLineService.findByLineNo(5L));
        log.info("{}", masterLineService.findByLineNo(5L));
        return "line/approvalLine";
    }

    @GetMapping("/addLine")
    public String addline(Model model) {
        // 로그인 한 사용자 regMemberNo가 getList의 parameter로
        model.addAttribute("masterList", masterLineService.getList(1L));
        // 특정 lineNo에 해당하는 subLineList를 가져옴
        model.addAttribute("subLineList", subLineService.getListByLineNo(1L));
        model.addAttribute("subMemberName", subLineService);
//        for (Long i = 0L; i < subLineService.getList().size(); i++) {
//            model.addAttribute("subMemberName", subLineService.getListByLineNo((i)));
//            log.info("{}", subLineService.getListByLineNo((i)));
//        }
        //model.addAttribute("deptName", subLineService.findBySubLineNo(1L).getDeptName());
        return "line/addLine";
    }
}