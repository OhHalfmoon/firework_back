package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.config.auth.CustomUserDetails;
import com.ohalfmoon.firework.dto.approval.ApprovalPageDto;
import com.ohalfmoon.firework.dto.approval.ApprovalResponseDto;
import com.ohalfmoon.firework.dto.attend.AttendResponseDTO;
import com.ohalfmoon.firework.dto.attend.AttendSaveDTO;
import com.ohalfmoon.firework.dto.fileUpload.AttachSaveDto;
import com.ohalfmoon.firework.dto.member.MemberLoginDTO;
import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.service.ApprovalService;
import com.ohalfmoon.firework.service.AttendService;
import com.ohalfmoon.firework.service.BoardService;
import com.ohalfmoon.firework.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
@Slf4j
@RequiredArgsConstructor
public class IndexController {
    private final ApprovalService approvalService;
    private final MemberService memberService;
    private final AttendService attendService;
    private final BoardService boardService;

    @GetMapping
    public String index(HttpSession session, @AuthenticationPrincipal CustomUserDetails details, Model model) {
        model.addAttribute("approvalState", approvalService.getStateList(details.getUserNo(), 1));
        model.addAttribute("boardList", boardService.getListTop());
        log.info("security session : {}", model.getAttribute("user"));
        log.info("session :{}", session.getAttribute("member"));
        return "index";
    }

    @PostMapping
    public String modifySign(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal CustomUserDetails user) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());

        AttachSaveDto saveDto = AttachSaveDto.builder()
                .originName(file.getOriginalFilename())
                .uuid(uuid)
                .ext(ext)
                .build();
        log.info("dto : {}", saveDto);
        log.info("file : {}", file);
        log.info("userNo : {}", saveDto);
        memberService.updateSign(saveDto, file, user.getUserNo());
        return "redirect:/auth/mypage";
    }



    @GetMapping("approvalList")
    @ResponseBody
    public ApprovalPageDto findApprovals(@AuthenticationPrincipal CustomUserDetails details, @PageableDefault(size = 5,
            direction = Sort.Direction.DESC,
            sort = "approvalNo") Pageable pageable) {
        Page<ApprovalEntity> entities = approvalService.getStateListByPaging(details.getUserNo(), 1, pageable);
        return new ApprovalPageDto(new PageResponseDTO<>(entities), entities.map(ApprovalResponseDto::new));
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

}