package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.board.BoardResponseDTO;
import com.ohalfmoon.firework.dto.board.BoardSaveDTO;
import com.ohalfmoon.firework.dto.board.BoardUpdateDTO;
import com.ohalfmoon.firework.dto.fileUpload.AttachSaveDto;
import com.ohalfmoon.firework.dto.paging.PageRequestDTO;
import com.ohalfmoon.firework.model.AttachEntity;
import com.ohalfmoon.firework.model.BoardEntity;
import com.ohalfmoon.firework.model.spec.BoardSpec;
import com.ohalfmoon.firework.persistence.AttachRepository;
import com.ohalfmoon.firework.persistence.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : BoardService
 * author         : 이지윤
 * date           : 2023/06/22
 * description    : 저장, 단일 조회, 리스트 조회, 수정, 삭제 기능, 첨부파일
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/22        이지윤           최초 생성
 * 2023/06/23        이지윤           업데이트 서비스 수정
 * 2023/06/26        이지윤           페이징, 검색, 첨부파일 추가
 */

@Service
@Slf4j
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private AttachRepository attachRepository;

    private final String projectPath = new File("").getAbsolutePath();

    @Value("upload")
    private String uploadDir;

    @Transactional
    public Long save(BoardSaveDTO boardSaveDTO, AttachSaveDto attachSaveDto, MultipartFile uploadFile) throws IOException {
        Long boardNo = boardRepository.save(boardSaveDTO.toEntity()).getBoardNo();

        String filePath = filePath(attachSaveDto.getUuid(), attachSaveDto.getExt());
        attachSaveDto.setPath(filePath);
        attachSaveDto.setBoardNo(boardNo);
        uploadFile.transferTo(new File(projectPath + filePath));
        AttachEntity attachEntity = attachSaveDto.toEntity();
        Long attachNo = attachEntity.getAttachNo();

        attachRepository.save(attachEntity);

        return boardNo;
    }

    private String filePath(String uuid, String ext) {
        File uploadFolder = new File(projectPath + uploadDir);
        if(!uploadFolder.exists()) {
            boolean mkdirs = uploadFolder.mkdirs();
        }
        return File.separator + uploadDir + File.separator + uuid + "." + ext;
    }
    public void addAttachEntity(Long boardNo) {

    }


    public List<BoardResponseDTO> getList() {
        return boardRepository.findAll().stream()
                .map(BoardResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<BoardResponseDTO> getListTop() {
        return boardRepository.findTop5ByOrderByBoardNoDesc().stream()
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

//    public Page<BoardEntity> getBoardList(Pageable pageable) {
//        return boardRepository.findAll(pageable);
//    }

//            (Sort.by(Sort.Order.desc("formNo")))
//            );
    public Page<BoardEntity> searchBoardList(PageRequestDTO dto) {
        return boardRepository.findAll(
                BoardSpec.boardSearch(dto.getType(), dto.getKeyword()), dto.getPageable(Sort.by(Sort.Order.desc("boardNo")))
        );
    }


}
