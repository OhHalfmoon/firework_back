package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.form.FormResponseDto;
import com.ohalfmoon.firework.dto.form.FormSaveDto;
import com.ohalfmoon.firework.dto.form.FormUpdateDto;
import com.ohalfmoon.firework.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : FormController
 * author         : 방한솔
 * date           : 2023/06/05
 * description    : 결재양식 API 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/05        방한솔            최초 생성
 */
@RequiredArgsConstructor
@RequestMapping("/api/form")
@RestController
public class FormApiController {
    private final FormService formService;

    @GetMapping("/{formNo}")
    public FormResponseDto findByFormNo(@PathVariable Long formNo){
        return formService.findByFormNo(formNo);
    }

    @PostMapping("/admin")
    public Long save(@RequestBody FormSaveDto dto) {
        Long save = formService.save(dto);

        return save;
    }

    @PutMapping("/admin/{formNo}")
    public Long update(@PathVariable Long formNo, @RequestBody FormUpdateDto dto) {
        Long update = formService.update(formNo, dto);

        return update;
    }

    @DeleteMapping("/admin/{formNo}")
    public void delete(@PathVariable Long formNo) {
        formService.delete(formNo);
    }


}
