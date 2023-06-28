package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.config.auth.CustomUserDetails;
import com.ohalfmoon.firework.config.auth.CustomUserDetailsService;
import com.ohalfmoon.firework.dto.approval.ApprovalPageDto;
import com.ohalfmoon.firework.dto.approval.ApprovalResponseDto;
import com.ohalfmoon.firework.dto.fileUpload.AttachSaveDto;
import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.service.ApprovalService;
import com.ohalfmoon.firework.service.AttendService;
import com.ohalfmoon.firework.service.BoardService;
import com.ohalfmoon.firework.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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
@RequestMapping("/")
@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {
    private final ApprovalService approvalService;
    private final MemberService memberService;
    private final AttendService attendService;
    private final BoardService boardService;
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping
    public String index(HttpSession session, @AuthenticationPrincipal CustomUserDetails details, Model model) {

        model.addAttribute("boardList", boardService.getListTop());
//        model.addAttribute("approvalState", approvalService.getStateList(details.getUserNo(), 1));
//        List<ApprovalLineDto> approvalLineDto = approvalService.getApprovalUserName(approvalNo);
//        model.addAttribute("approUserList", approvalLineDto);
//        List<MemberResponseDTO> memberResponseDTOS = memberService.getMemberList()
//        model.addAttribute("memberSign", memberService.get(details.getUserNo()));
//        log.info("security session : {}", model.getAttribute("user"));
//        log.info("session :{}", session.getAttribute("member"));
        return "index";
    }

    @PostMapping
    public String modifySign(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal CustomUserDetails user, HttpSession session) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());

        AttachSaveDto saveDto = AttachSaveDto.builder()
                .originName(file.getOriginalFilename())
                .uuid(uuid)
                .ext(ext)
                .build();
//        log.info("dto : {}", saveDto);
//        log.info("file : {}", file);
//        log.info("user : {}", user.getAttachNo());
        memberService.updateSign(saveDto, file, user.getUserNo());
//        log.info("newSign : {}", user.getAttachNo());
        // session reset start
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // session값 가져옴
        CustomUserDetails details = (CustomUserDetails) customUserDetailsService.loadUserByUsername(authentication.getName()); // 현재 사용자의 정보를 CustomUserDetails에 담음?
        UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                details, authentication.getCredentials(), details.getAuthorities()); // session값 설정
        SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken); // 인증 토큰 설정
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, // 세션 저장
                SecurityContextHolder.getContext());
        // session reset end
        return "redirect:/";
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