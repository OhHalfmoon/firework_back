package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.FormResponseDto;
import com.ohalfmoon.firework.dto.FormSaveDto;
import com.ohalfmoon.firework.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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
 * 2023/06/08        방한솔           양식등록 GET/POST 추가
 */
@RequiredArgsConstructor
@RequestMapping("/admin/form")
@Controller
public class FormViewController {

    private final FormService formService;

    @GetMapping("/detail/{formNo}")
    public String detail(@PathVariable Long formNo, Model model){
        FormResponseDto formDetail = formService.findByFormNo(formNo);

        model.addAttribute("formDetail", formDetail);

        return "admin/form/detail";
    }

    @GetMapping
    public String list(
            Optional<String> formName
            , Pageable pageRequest
            , Model model
    ) {
        Page<FormResponseDto> formDtos = formService.searchFormList(formName, pageRequest);

        List<FormResponseDto> content = formDtos.getContent();

        model.addAttribute("dtoList", content);
        model.addAttribute("pageInfo", formDtos.getPageable());

        return "admin/form/list";
    }

    @GetMapping("/add")
    public String addForm() {
        return "admin/form/form-add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute FormSaveDto saveDto){
        Long save = formService.save(saveDto);

        return "redirect:/admin/form/detail/" + save;
    }
}
