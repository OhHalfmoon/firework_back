package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.FormResponseDto;
import com.ohalfmoon.firework.dto.FormSaveDto;
import com.ohalfmoon.firework.dto.FormUpdateDto;
import com.ohalfmoon.firework.dto.paging.PageRequestDTO;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            PageRequestDTO pageRequestDTO,
            Model model
    ) {
        Page<FormEntity> pageDto = formService.searchFormList(pageRequestDTO);

        List<FormResponseDto> formList = pageDto.getContent().stream().map(FormResponseDto::new).collect(Collectors.toList());

        PageResponseDTO<FormEntity> pageResponse = new PageResponseDTO<>(pageDto, pageRequestDTO);

        log.info("pageResponse : {}", pageResponse);

        model.addAttribute("formList", formList);
        model.addAttribute("pageDto", pageResponse);

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

    @GetMapping("/update/{formNo}")
    public String updateForm(@PathVariable Long formNo, Model model) {
        FormResponseDto formDto = formService.findByFormNo(formNo);

        model.addAttribute("formDto", formDto);

        return "admin/form/form-update";
    }

    @PutMapping(value = "update/{formNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable Long formNo, @RequestBody FormUpdateDto updateDto){
        Long update = formService.update(formNo, updateDto);

        FormResponseDto byFormNo = formService.findByFormNo(formNo);

        if(update > 0){
            return ResponseEntity.ok().body(byFormNo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{formNo}")
    @ResponseBody
    public ResponseEntity<?> deleteForm(@PathVariable Long formNo) {
        Map<String, Object> body = new HashMap<>();
        body.put("formNo", formNo);

        if(formService.delete(formNo)){
            return ResponseEntity.ok().body(body);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
