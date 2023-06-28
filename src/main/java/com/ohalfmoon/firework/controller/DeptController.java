package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.JsTreeNodeDto;
import com.ohalfmoon.firework.dto.JsTreeState;
import com.ohalfmoon.firework.dto.dept.*;
import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.dto.member.MemberUpdateDeptDto;
import com.ohalfmoon.firework.service.DeptService;
import com.ohalfmoon.firework.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : DeptController
 * author         : banghansol
 * date           : 2023/06/22
 * description    : 부서 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/22       방한솔            최초 생성
 */
@RequiredArgsConstructor
@RequestMapping("/admin/dept")
@Controller
@Slf4j
public class DeptController {

    private final MemberService memberService;

    private final DeptService deptService;

    @GetMapping
    public String getDeptPage(){
        return "admin/dept/list";
    }

    @GetMapping("get-dept")
    @ResponseBody
    public ResponseEntity<?> getDept()  {

        List<JsTreeNodeDto<DeptListResponseDTO>> list = deptService.deptWithCount().stream()
                .map(dept -> JsTreeNodeDto.<DeptListResponseDTO>builder()
                        .id("dept" + dept.getDeptNo())
                        .text(dept.getDeptName())
                        .parent("#")
                        .children(dept.getCount() != 0)
                        .state(JsTreeState.builder()
                                .opened(false)
                                .build())
                        .data(dept)
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @GetMapping("get-member")
    @ResponseBody
    public ResponseEntity<?> getMember(@RequestParam("deptNo") Long deptNo){
        log.info("deptNo : {}", deptNo);

        List<JsTreeNodeDto<MemberResponseDTO>> list = memberService.getMemberListByDeptNo(deptNo)
                .stream().map(member -> JsTreeNodeDto.<MemberResponseDTO>builder()
                        .id("member" + member.getUserNo())
                        .parent("dept" + deptNo)
                        .text(member.getName())
                        .icon("fas fa-user")
                        .children(false)
                        .state(JsTreeState.builder()
                                .opened(false)
                                .build())
                        .data(member)
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/updateMemDept")
    @ResponseBody
    public ResponseEntity<?> updateMemberDept(@RequestBody MemberUpdateDeptDto dto) {
        log.info("dto : {}", dto);

        boolean updateResult = memberService.updateDept(dto);

        Map<String, Object> result = new HashMap<>();
        result.put("result", updateResult);

        if(updateResult){
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseEntity<?> insertDept(@RequestBody DeptSaveDto dto){
        boolean saveResult = deptService.insertDept(dto);

        Map<String, Object> result = new HashMap<>();
        result.put("result", saveResult);

        if(saveResult){
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }

    }

    @PutMapping("/updateDept")
    @ResponseBody
    public ResponseEntity<?> updateDept(@RequestBody DeptUpdateDto dto) {
        boolean updateResult = deptService.updateDept(dto);

        Map<String, Object> result = new HashMap<>();
        result.put("result", updateResult);

        if(updateResult){
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDept(@RequestBody DeptDeleteDto dto) {
        Map<String, Object> result = new HashMap<>();

        try {
            boolean deleteResult = deptService.deleteDept(dto);
            result.put("result", deleteResult);

            if(deleteResult) {

                return ResponseEntity.ok().body(result);
            } else {
                result.put("message", "회원수가 없는 부서만 삭제 할 수 있습니다!");

                return ResponseEntity.badRequest().body(result);
            }

        } catch (Exception e){
            result.put("error", "부서 삭제 중 오류가 발생했습니다.");

            return ResponseEntity.internalServerError().body(result);
        }

    }

}
