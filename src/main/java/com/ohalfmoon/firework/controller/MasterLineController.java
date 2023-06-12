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
    public String addline(Model model){
        model.addAttribute("master", masterLineService.getList(1L));
        model.addAttribute("line", subLineService.getList());
        return "line/addLine";
    }


}