package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todo")
    public String todo() {
        return "/todo/calendar";
    }

}
