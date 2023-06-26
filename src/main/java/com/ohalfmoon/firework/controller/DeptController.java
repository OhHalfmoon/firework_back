package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.JsTreeNodeDto;
import com.ohalfmoon.firework.dto.JsTreeState;
import com.ohalfmoon.firework.dto.dept.DeptListResponseDTO;
import com.ohalfmoon.firework.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping
    public String getDeptPage(){
        return "admin/dept/list";
    }

    @GetMapping("get-dept")
    @ResponseBody
    public ResponseEntity<?> getDept()  {
        List<JsTreeNodeDto<DeptListResponseDTO>> list = new ArrayList<>();

        JsTreeNodeDto<DeptListResponseDTO> dept1 = JsTreeNodeDto.<DeptListResponseDTO>builder()
                .id("dept1")
                .text("영업팀")
                .parent("#")
                .children(true)
                .state(JsTreeState.builder()
                        .opened(false)
                        .build())
                .data(DeptListResponseDTO.builder()
                        .deptNo(1L)
                        .deptName("영업팀")
                        .build())
                //.state()
                //.icon()
                .build();

        JsTreeNodeDto<DeptListResponseDTO> dept2 = JsTreeNodeDto.<DeptListResponseDTO>builder()
                .id("dept2")
                .text("총무팀")
                .parent("#")
                .children(true)
                .data(DeptListResponseDTO.builder()
                        .deptNo(2L)
                        .deptName("총무팀")
                        .build())
                .build();

        JsTreeNodeDto<DeptListResponseDTO> dept3 = JsTreeNodeDto.<DeptListResponseDTO>builder()
                .id("dept3")
                .text("개발팀")
                .parent("#")
                .children(false)
                .data(DeptListResponseDTO.builder()
                        .deptNo(3L)
                        .deptName("개발팀")
                        .build())
                .build();

        list.add(dept1);
        list.add(dept2);
        list.add(dept3);


        return ResponseEntity.ok(list);
    }

    @GetMapping("get-member")
    @ResponseBody
    public ResponseEntity<?> getMember(){
    /*
        {
            id: "user3",
            parent: "dept1",
            text: "테스트유저3",
            data: {
                memberNo: 3,
                memberName: "테스트유저3"
            }
         }
        */

        // MemberResponseDTO dto = memberService.getMemberList().get(0);

        List<JsTreeNodeDto<String>> list = new ArrayList<>();

        JsTreeNodeDto<String> member1 = JsTreeNodeDto.<String>builder()
                .id("1")
                .text("회원1")
                //.state()
                //.icon()
                .build();

        JsTreeNodeDto<String> member2 = JsTreeNodeDto.<String>builder()
                .id("2")
                .text("회원2")
                //.state()
                //.icon()
                .build();

        JsTreeNodeDto<String> member3 = JsTreeNodeDto.<String>builder()
                .id("3")
                .text("회원3")
                //.state()
                //.icon()
                .build();

        list.add(member1);
        list.add(member2);
        list.add(member3);


        return ResponseEntity.ok().body(list);
    }
}
