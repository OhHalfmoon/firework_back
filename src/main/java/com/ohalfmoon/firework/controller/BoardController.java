package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.board.BoardResponseDTO;
import com.ohalfmoon.firework.dto.board.BoardSaveDTO;
import com.ohalfmoon.firework.dto.board.BoardUpdateDTO;
import com.ohalfmoon.firework.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String save(BoardSaveDTO board) {
        log.info("board={}", board);
        boardService.save(board);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("boardList", boardService.getList());
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
