package com.ohalfmoon.firework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : FormViewController
 * author         : 방한솔
 * date           : 2023/06/05
 * description    : 결재양식 뷰 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/05        방한솔           최초 생성
 */
@RequestMapping("/admin/form")
@Controller
public class FormViewController {
    @GetMapping("/detail/{formNo}")
    public String detail(@PathVariable Long formNo){
        return "admin/form/detail";
    }
}
