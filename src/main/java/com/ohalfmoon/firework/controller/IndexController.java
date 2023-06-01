package com.ohalfmoon.firework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : IndexController
 * author         : banghansol
 * date           : 2023/06/01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/01        banghansol       최초 생성
 */
@Controller("/")
public class IndexController {
    @GetMapping
    public String index(){
        return "index";
    }
}
