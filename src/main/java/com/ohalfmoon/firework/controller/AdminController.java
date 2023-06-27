package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.MemberPageDto;
import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.dto.member.MemberUpdateByAdminRequestDTO;
import com.ohalfmoon.firework.dto.paging.PageRequestDTO;
import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.Role;
import com.ohalfmoon.firework.model.State;
import com.ohalfmoon.firework.service.AttendService;
import com.ohalfmoon.firework.service.DeptService;
import com.ohalfmoon.firework.service.MemberService;
import com.ohalfmoon.firework.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName :  com.ohalfmoon.firework.controller
 * fileName : AdminController
 * author :  ycy
 * date : 2023-06-15
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-15                ycy             최초 생성
 */
@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final MemberService memberService;

    private final AttendService attendService;

    private final PositionService positionService;

    private final DeptService deptService;

    /**
     * 회원 상세페이지 조회
     *
     * @param model the model
     * @param dto   the dto
     * @return the member
     */
    @GetMapping("/member/{userNo}")
    public String getMember(Model model, MemberResponseDTO dto) {
        model.addAttribute("member", memberService.get(dto.getUserNo()));
        model.addAttribute("attend", attendService.getAttend(dto.getUserNo()));
        model.addAttribute("dept", deptService.deptList());
        model.addAttribute("position", positionService.positionList());
        model.addAttribute("role", Role.values());
        model.addAttribute("state", State.values());
        return "/admin/member/getMember";
    }

    /**
     * 회원정보 수정(관리자)
     *
     * @param userNo the user no
     * @param dto    the dto
     * @return the string
     */
    @PostMapping("/member/{userNo}")
    public String updateByAdmin(Long userNo, MemberUpdateByAdminRequestDTO dto) {
        log.info("dto 확인 : {}", dto);
        memberService.updateByAdmin(userNo, dto);
        return "redirect:/admin/member/"+userNo;
    }

    /**
     * 상태값에 따른 회원 list 출력
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/member")
    public String member(Model model, PageRequestDTO dto, @PageableDefault(size = 10)Pageable pageable) {
        Page<MemberEntity> entities = memberService.getStateByZero(pageable);
        Page<MemberEntity> entities2 = memberService.getStateByOne(pageable);
        Page<MemberEntity> entities3 = memberService.getStateByTwo(pageable);
        MemberPageDto memberPageDto = new MemberPageDto(new PageResponseDTO<>(entities), entities.map(MemberResponseDTO::new));
        MemberPageDto memberPageDto2 = new MemberPageDto(new PageResponseDTO<>(entities2), entities2.map(MemberResponseDTO::new));
        MemberPageDto memberPageDto3 = new MemberPageDto(new PageResponseDTO<>(entities3), entities3.map(MemberResponseDTO::new));
        model.addAttribute("stateByZeroUser", memberPageDto);
        model.addAttribute("stateByOneUser", memberPageDto2);
        model.addAttribute("stateByTwoUser", memberPageDto3);
//        model.addAttribute("stateByZeroUser", memberService.getStateByZero());
//        model.addAttribute("stateByOneUser", memberService.getStateByOne());
//        model.addAttribute("stateByTwoUser", memberService.getStateByTwo());

        return "/admin/member/member";
    }
}
