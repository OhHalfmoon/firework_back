package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.board.BoardResponseDTO;
import com.ohalfmoon.firework.dto.board.BoardSaveDTO;
import com.ohalfmoon.firework.dto.board.BoardUpdateDTO;
import com.ohalfmoon.firework.dto.paging.PageRequestDTO;
import com.ohalfmoon.firework.model.AlarmEntity;
import com.ohalfmoon.firework.model.BoardEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.spec.BoardSpec;
import com.ohalfmoon.firework.persistence.AlarmRepository;
import com.ohalfmoon.firework.persistence.AttachRepository;
import com.ohalfmoon.firework.persistence.BoardRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
 * 2023/06/27        이지윤           첨부파일 CRUD
 */

@Service
@Slf4j
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private AttachRepository attachRepository;

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AttachService attachService;

    private final String projectPath = new File("").getAbsolutePath();

    @Value("upload")
    private String uploadDir;

    private String filePath(String uuid, String ext) {
        File uploadFolder = new File(projectPath + uploadDir);
        if(!uploadFolder.exists()) {
            boolean mkdirs = uploadFolder.mkdirs();
        }
        return File.separator + uploadDir + File.separator + uuid + "." + ext;
    }

    // 트랜잭션 전파
    @Transactional(propagation = Propagation.REQUIRED)
    public Long save(BoardSaveDTO boardSaveDTO, List<MultipartFile> files) throws IOException {
        // 0. files가 들어왔는지 체크하여 파일저장로직을 실행(조건문)
        // 1. 실제 파일을 files를 순환하면서 실제로 로컬 저장소에 저장
        // 2. dto List가 있으므로 이 dto들을 엔티티로 변환하여 DB에 저장

        // 파일 저장시 문제가 생겼을 경우 UnexpectedRollbackException 발생

        // boardNo 생성
        Long boardNo = boardRepository.save(boardSaveDTO.toEntity()).getBoardNo();

        if(!files.get(0).isEmpty()) {
//            attachService.updateBoardFileList(boardNo, files);
            attachService.fileListSave(files, boardNo);
        }

        return boardNo;

//        if(attachSaveDtos != null) {
//            for (AttachSaveDto attachSaveDto : attachSaveDtos) {
//                String filePath = filePath(attachSaveDto.getUuid(), attachSaveDto.getExt());
//                attachSaveDto.setPath(filePath);
//
//                attachSaveDto.setBoardNo(boardNo);
//                uploadFile.transferTo(new File(projectPath + filePath));
//                AttachEntity attachEntity = attachSaveDto.toEntity();
//
//                attachRepository.save(attachEntity);
//            }
////            String filePath = filePath(attachSaveDto.getUuid(), attachSaveDto.getExt());
////            attachSaveDto.setPath(filePath);
////
////            attachSaveDto.setBoardNo(boardNo);
////            uploadFile.transferTo(new File(projectPath + filePath));
////            AttachEntity attachEntity = attachSaveDto.toEntity();
////            attachRepository.save(attachEntity);
//        }
//        List<MemberEntity> memberEntities = memberRepository.findAll();
//


//        for (MemberEntity i : memberEntities) {
//            alarmRepository.save(AlarmEntity.builder()
//                    .alarmReceiver(i)
//                    .alarmTitle("새로운 공지사항-" + boardNo)
//                    .alarmCategory("공지사항")
//                    .boardNo(BoardEntity.builder().boardNo(boardNo).build())
//                    .approvalNo(null)
//                    .build());
//        }
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

    @Transactional(propagation = Propagation.REQUIRED)
    public Long update(Long boardNo, BoardUpdateDTO boardUpdateDTO, List<MultipartFile> files) throws IOException {
        BoardEntity boardEntity = boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. boardNo=" + boardNo));
        boardEntity.update(
                boardNo
                , boardUpdateDTO.getBoardTitle()
                , boardUpdateDTO.getBoardContent()
        );

        if(!files.get(0).isEmpty()) {
            attachService.updateBoardFileList(boardNo, files);
        }

        List<MemberEntity> memberEntities = memberRepository.findAll();

        for (MemberEntity i : memberEntities) {
            alarmRepository.save(AlarmEntity.builder()
                    .alarmReceiver(i)
                    .alarmTitle("수정된 공지사항-" + boardNo)
                    .alarmCategory("공지사항")
                    .boardNo(BoardEntity.builder().boardNo(boardNo).build())
                    .approvalNo(null)
                    .build());
        }
        return boardNo;
    }

    @Transactional
    public void delete(Long boardNo) {
        BoardEntity boardEntity = boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. boardNo=" + boardNo));

        alarmRepository.deleteByBoardNo(boardEntity);
        if(attachRepository.findAllByBoardEntity(boardEntity).size() > 0) {
            attachRepository.deleteBoardEntitiesByBoardEntity_BoardNo(boardNo);
        }
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
