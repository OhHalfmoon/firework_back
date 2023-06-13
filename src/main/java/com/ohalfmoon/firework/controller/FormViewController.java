package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.FormResponseDto;
import com.ohalfmoon.firework.dto.FormSaveDto;
import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import com.ohalfmoon.firework.model.FormEntity;
import com.ohalfmoon.firework.service.FormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
 * 2023/06/12        방한솔           페이징 기능 추가
 */
@RequiredArgsConstructor
@RequestMapping("/admin/form")
@Controller
@Slf4j
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
            Optional<String> formName,
            @PageableDefault(page = 1)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "formNo", direction = Sort.Direction.DESC)
            })
            Pageable pageRequest,
            Model model
    ) {
        Page<FormEntity> pageDto = formService.searchFormList(formName, pageRequest);

        List<FormResponseDto> formList = pageDto.getContent().stream().map(FormResponseDto::new).collect(Collectors.toList());

        PageResponseDTO<FormEntity> pageDTO = new PageResponseDTO<>(pageDto);

        log.info("pageDTO : {}", pageDTO);

        model.addAttribute("formList", formList);
        model.addAttribute("pageDto", pageDTO);

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
