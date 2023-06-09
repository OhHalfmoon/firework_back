package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.board.BoardResponseDTO;
import com.ohalfmoon.firework.dto.board.BoardSaveDTO;
import com.ohalfmoon.firework.dto.board.BoardUpdateDTO;
import com.ohalfmoon.firework.dto.fileUpload.AttachDto;
import com.ohalfmoon.firework.dto.paging.PageRequestDTO;
import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import com.ohalfmoon.firework.model.BoardEntity;
import com.ohalfmoon.firework.service.AttachService;
import com.ohalfmoon.firework.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : BoardController
 * author         : 이지윤
 * date           : 2023/06/22
 * description    : 게시판 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/22        이지윤            최초 생성
 * 2023/06/23        이지윤            단일 조회, 수정, 삭제, 저장 추가
 * 2023/06/26        이지윤            페이징, 검색, 첨부파일 추가
 * 2023/06/27        이지윤            첨부파일 CRUD
 */

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {
    private final BoardService boardService;

    private final AttachService attachService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TL', 'ROLE_CEO')")
    @GetMapping("/save")
    public void register() {}

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TL', 'ROLE_CEO')")
    @PostMapping("/save")
    public String save(BoardSaveDTO boardDTO, @RequestParam("file") List<MultipartFile> files) throws IOException {
        log.info("board={}", boardDTO);
        log.info("files = {}", files);

//        List<AttachSaveDto> attachSaveDtos = null;

        if(!files.isEmpty()){
            log.info("파일 존재!");

            try{

                boardService.save(boardDTO, files);
            } catch (UnexpectedRollbackException e){
                throw new RuntimeException("글 저장 중 문제가 발생하였습니다!");
            }


//            for(MultipartFile file : files) {
//                String uuid = UUID.randomUUID().toString();
//                String ext = FilenameUtils.getExtension(file.getOriginalFilename());
//
//                attachSaveDto = AttachSaveDto.builder()
//                        .originName(file.getOriginalFilename())
//                        .uuid(uuid)
//                        .ext(ext)
//                        .build();
//            }
            // DB저장용 DTO가 필요 : MultipartFile -> DTO
        }

//        log.info("saveAttachDto={}", attachSaveDto);
//        boardService.save(boardDTO, attachSaveDto, file);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String getList(Model model, PageRequestDTO pageRequestDTO) {
        Page<BoardEntity> pageDto = boardService.searchBoardList(pageRequestDTO);

        List<BoardResponseDTO> boardList = pageDto.getContent().stream().map(BoardResponseDTO::new).collect(Collectors.toList());

        PageResponseDTO<BoardEntity> pageResponseDTO = new PageResponseDTO<>(pageDto, pageRequestDTO);

        log.info("pageResponseDTO={}", pageResponseDTO);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageDTO", pageResponseDTO);

        return "board/list";
    }

    @GetMapping("/view/{boardNo}")
    public String get(Model model, @PathVariable Long boardNo) {
        log.info("view boardNo={}", boardNo);
        model.addAttribute("board", boardService.get(boardNo));
        model.addAttribute("fileList", attachService.getBoardFileList(boardNo));

//        log.info("fileList : {}", attachService.getFileListByBoardNo(boardNo));
        return "board/view";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TL', 'ROLE_CEO')")
    @GetMapping("/modify/{boardNo}")
    public String modify(Model model, @PathVariable Long boardNo) {
        log.info("modify");
        model.addAttribute("board", boardService.get(boardNo));
//        model.addAttribute("fileList", attachService.getFileListByBoardNo(boardNo));
        return "board/modify";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TL', 'ROLE_CEO')")
    @PostMapping("/modify/{boardNo}")
    public String modify(@PathVariable Long boardNo, @ModelAttribute BoardUpdateDTO boardUpdateDTO, @RequestParam("file") List<MultipartFile> file) throws IOException {
        log.info("board={}", boardUpdateDTO);
        log.info("modify boardNo={}", boardNo);
        boardService.update(boardNo, boardUpdateDTO, file);
        return "redirect:/board/list";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TL', 'ROLE_CEO')")
    @PostMapping("/remove")
    public String remove(Long boardNo) {
        log.info("remove boardNo={}", boardNo);
        boardService.delete(boardNo);
        return "redirect:/board/list";
    }

}
