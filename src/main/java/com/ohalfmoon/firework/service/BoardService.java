package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.board.BoardResponseDTO;
import com.ohalfmoon.firework.dto.board.BoardSaveDTO;
import com.ohalfmoon.firework.persistence.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public Long save(BoardSaveDTO boardSaveDTO) {
        return boardRepository.save(boardSaveDTO.toEntity()).getBoardNo();
    }

    public List<BoardResponseDTO> getList() {
        return boardRepository.findAll().stream()
                .map(BoardResponseDTO::new)
                .collect(Collectors.toList());
    }

    public BoardResponseDTO get(Long boardNo) {
        return new BoardResponseDTO(boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. boardNo=" + boardNo)));
    }

    public Long update(Long boardNo, BoardSaveDTO boardSaveDTO) {
        return boardRepository.save(boardSaveDTO.toEntity()).getBoardNo();
    }

    public void delete(Long boardNo) {
        boardRepository.deleteById(boardNo);
    }

}
