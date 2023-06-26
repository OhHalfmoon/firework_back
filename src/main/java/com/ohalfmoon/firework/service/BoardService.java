package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.board.BoardResponseDTO;
import com.ohalfmoon.firework.dto.board.BoardSaveDTO;
import com.ohalfmoon.firework.dto.board.BoardUpdateDTO;
import com.ohalfmoon.firework.model.BoardEntity;
import com.ohalfmoon.firework.persistence.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : BoardService
 * author         : 이지윤
 * date           : 2023/06/22
 * description    : 저장, 단일 조회, 리스트 조회, 수정, 삭제 기능
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/22        이지윤           최초 생성
 * 2023/06/23        이지윤           업데이트 서비스 수정
 */

@Service
@Slf4j
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
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

    @Transactional
    public Long update(Long boardNo, BoardUpdateDTO boardUpdateDTO) {
        BoardEntity boardEntity = boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. boardNo=" + boardNo));
        boardEntity.update(boardNo, boardUpdateDTO.getBoardTitle(), boardUpdateDTO.getBoardContent());
        return boardNo;
    }

    @Transactional
    public void delete(Long boardNo) {
        boardRepository.deleteById(boardNo);
    }

}
