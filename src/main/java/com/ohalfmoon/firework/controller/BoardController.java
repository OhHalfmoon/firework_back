package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.MessageResponseDto;
import com.ohalfmoon.firework.dto.board.BoardPageDTO;
import com.ohalfmoon.firework.dto.board.BoardResponseDTO;
import com.ohalfmoon.firework.dto.board.BoardSaveDTO;
import com.ohalfmoon.firework.dto.board.BoardUpdateDTO;
import com.ohalfmoon.firework.dto.fileUpload.AttachSaveDto;
import com.ohalfmoon.firework.dto.paging.PageRequestDTO;
import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import com.ohalfmoon.firework.model.BoardEntity;
import com.ohalfmoon.firework.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
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
 */

@Controller
@Slf4j
@RequestMapping("board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/save")
    public void register() {}

    @PostMapping("/save")
    public String save(BoardSaveDTO board, @RequestParam("file") MultipartFile file) throws IOException {
        log.info("board={}", board);

        String uuid = UUID.randomUUID().toString();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());

        AttachSaveDto saveAttachDto = AttachSaveDto.builder()
                        .originName(file.getOriginalFilename())
                        .uuid(uuid)
                        .ext(ext)
                        .build();
        log.info("saveAttachDto={}", saveAttachDto);
        log.info("files={}", file);
        log.info("files.getOriginalFilename()={}", file.getOriginalFilename());
        boardService.save(board, saveAttachDto, file);
        return "redirect:/board/list";
    }

//    @GetMapping("/list")
//    public String getList(Model model, @PageableDefault(size = 10, direction = Sort.Direction.DESC, sort = "boardNo") Pageable pageable) {
//        Page<BoardEntity> entities = boardService.getBoardList(pageable);
//        BoardPageDTO boardPageDTO = new BoardPageDTO(new PageResponseDTO<>(entities), entities.map(BoardResponseDTO::new));
//        model.addAttribute("boardList", boardPageDTO);
//        log.info("boardPageDTO={}", boardPageDTO.getBoardList().getContent());
//        return "/board/list";
//    }

    @GetMapping("/list")
    public String getList(Model model, PageRequestDTO pageRequestDTO) {
        Page<BoardEntity> pageDto = boardService.searchBoardList(pageRequestDTO);

        List<BoardResponseDTO> boardList = pageDto.getContent().stream().map(BoardResponseDTO::new).collect(Collectors.toList());

        PageResponseDTO<BoardEntity> pageResponseDTO = new PageResponseDTO<>(pageDto, pageRequestDTO);

        log.info("pageResponseDTO={}", pageResponseDTO);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageDTO", pageResponseDTO);

        return "/board/list";
    }

    @GetMapping("/view/{boardNo}")
    public String get(Model model, @PathVariable Long boardNo) {
        log.info("view boardNo={}", boardNo);
        model.addAttribute("board", boardService.get(boardNo));
        return "/board/view";
    }

    @GetMapping("/modify/{boardNo}")
    public String modify(Model model, @PathVariable Long boardNo) {
        log.info("modify");
        model.addAttribute("board", boardService.get(boardNo));
        return "board/modify";
    }

    @PostMapping("/modify/{boardNo}")
    public String modify(@PathVariable Long boardNo, BoardUpdateDTO board) {
        log.info("board={}", board);
        log.info("modify boardNo={}", boardNo);
        boardService.update(boardNo, board);
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(Long boardNo) {
        log.info("remove boardNo={}", boardNo);
        boardService.delete(boardNo);
        return "redirect:/board/list";
    }

}