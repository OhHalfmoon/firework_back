package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.fileUpload.AttachDto;
import com.ohalfmoon.firework.dto.member.*;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.PositionEntity;
import com.ohalfmoon.firework.model.State;
import com.ohalfmoon.firework.persistence.AttachRepository;
import com.ohalfmoon.firework.persistence.DeptRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import com.ohalfmoon.firework.persistence.PositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * packageName :  com.ohalfmoon.firework.service
 * fileName : MemberService
 * author :  ycy
 * date : 2023-06-02
 * description : 회원 서비스
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-02                ycy             최초 생성
 * 2023-06-02                ycy            register 추가
 * 2023-06-06                ycy            update 추가
 * 2023-06-06                ycy            updatePw 추가
 * 2023-06-07                ycy            get 추가
 * 2023-06-07                ycy            login 추가
 * 2023-06-09                ycy            login 수정
 * 
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final DeptRepository deptRepository;

    private final PositionRepository positionRepository;

    private final AttachRepository attachRepository;

    private final String projectPath = new File("").getAbsolutePath();

    private final PasswordEncoder encoder;
    private final AttachService attachService;

    /**
     * 회원가입 기능
     *
     * @param memberDTO the member dto
     * @return the role entity
     */
    @Transactional // springboot
    public MemberEntity register(MemberDTO memberDTO) {
        memberDTO.setPassword(encoder.encode(memberDTO.getPassword()));
        MemberEntity entity = memberDTO.toEntity();

        DeptEntity byId = deptRepository
                .findById(memberDTO.getDeptNo())
                .orElseThrow(() -> new IllegalArgumentException(""));
        entity.updateDeptNo(byId);

        PositionEntity byId2 = positionRepository
                .findById(memberDTO.getPositionNo())
                .orElseThrow(() -> new IllegalArgumentException(""));
        entity.updatePositionNo(byId2);

        return memberRepository.save(entity);

}

    /**
     * 비밀번호 수정
     *
     * @param userNo the user no
     * @param dto    the dto
     * @return the long
     */
    @Transactional
    public String updatePw(Long userNo, MemberUpdatePwDTO dto) {
        MemberEntity entity = memberRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 id가 존재하지 않습니다." + userNo));
        dto.setPassword(encoder.encode(dto.getPassword()));
        if(encoder.matches(dto.getOldPw(), entity.getPassword())) {
            entity.updatePw(dto.getPassword());
            return "success";
        }
        else {
            return "error";
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Long updateSign(AttachDto adto, MultipartFile uploadFile, Long userNo) throws IOException {
//        String filePath =  filePath(adto.getUuid(), adto.getExt());
//        adto.setPath(filePath);
//        uploadFile.transferTo(new File(projectPath + filePath));
//        AttachEntity sign = adto.toEntity();
//        attachRepository.save(sign);

        Long signNo = attachService.upload(uploadFile, adto);

        //Long signNo = sign.getAttachNo();

        MemberEntity memberEntity = memberRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 id가 존재하지 않습니다." + userNo));

        memberEntity.updateAttachEntity(
                signNo
        );
        return userNo;

    }

    /**
     * 회원 단일조회 (userNo)
     *
     * @param userNo the user no
     * @return the long
     */
    public MemberResponseDTO get(Long userNo) {
        return memberRepository.findById(userNo)
                .map(MemberResponseDTO::new).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다."+userNo));
    }


    /**
     * (관리자)회원 state 변경
     *
     * @param userNo the user no
     * @param dto    the dto
     * @return the long
     */
    @Transactional
    public Long recognize(Long userNo, MemberUpdateStateDTO dto) {
        MemberEntity entity =
                memberRepository.findById(userNo)
                        .orElseThrow(() -> new IllegalArgumentException("해당 ID가 존재하지 않습니다." + userNo));
        entity.updateState(dto.getUserNo(), dto.getState());
        return userNo;
    }
    /**
     * 회원정보 수정
     *
     * @param userNo the user no
     * @param dto    the dto
     * @return the long
     */
    @Transactional
    public Long update(Long userNo, MemberUpdateDTO dto) {
        MemberEntity entity = memberRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 id가 존재하지 않습니다." + userNo));

        DeptEntity byId = deptRepository
                .findById(dto.getDeptNo())
                .orElseThrow(() -> new IllegalArgumentException(""));
        entity.updateDeptNo(byId);

        PositionEntity byId2 = positionRepository
                .findById(dto.getPositionNo())
                .orElseThrow(() -> new IllegalArgumentException(""));
        entity.updatePositionNo(byId2);

        entity.update(dto.getEmail(), dto.getPhoneNum(), dto.getName(), dto.getBirthdate(), dto.getStartdate());

        return userNo;
}


    /**
     * 로그인
     *
     * @param memberLoginDTO the member login dto
     * @return the member response dto
     */
    public MemberResponseDTO login (MemberLoginDTO memberLoginDTO) {
        MemberEntity entity = memberRepository.findByUsername(memberLoginDTO.getUsername());

//        if(entity != null && entity.getPassword().equals(memberLoginDTO.getPassword())) {
        if(entity != null && encoder.matches(memberLoginDTO.getPassword(), entity.getPassword())) {

            return new MemberResponseDTO(entity);
        }
        return null;
    }

    /**
     * 멤버 username(id) 단일조회
     *
     * @param username the username
     * @return the member entity
     */
    public MemberEntity get(final String username) {
        return memberRepository.findByUsername(username);
    }



    /**
     * 멤버 List 조회
     *
     * @return the member list
     */
    public List<MemberResponseDTO> getMemberList() {
        return memberRepository.findAll()
                .stream().map(MemberResponseDTO::new).collect(Collectors.toList());
    }

    /**
     * 회원정보 수정(관리자)
     *
     * @param userNo the user no
     * @param dto    the dto
     * @return the long
     */
    @Transactional
    public Long updateByAdmin(Long userNo, MemberUpdateByAdminRequestDTO dto) {
        MemberEntity entity = memberRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 id가 존재하지 않습니다." + userNo));

        DeptEntity byId = deptRepository
                .findById(dto.getDeptNo())
                .orElseThrow(() -> new IllegalArgumentException(""));
        entity.updateDeptNo(byId);

        PositionEntity byId2 = positionRepository
                .findById(dto.getPositionNo())
                .orElseThrow(() -> new IllegalArgumentException(""));
        entity.updatePositionNo(byId2);

        entity.updateByAdmin(dto.getRoleName(), dto.getState(), dto.isManager());

        return userNo;
    }

    /**
     * state값이 0인 회원 return
     *
     * @return the state by zero
     */
    public Page<MemberEntity> getStateByZero(Pageable pageable) {
        return memberRepository.findAllByState(State.WATING, pageable);
    }

    /**
     *state값이 1인 회원 return
     *
     * @return the state by one
     */
    public Page<MemberEntity> getStateByOne(Pageable pageable) {
        return memberRepository.findAllByState(State.APPROVAL, pageable);
    }

    /**
     * state값이 2인 회원 return
     *
     * @return the state by two
     */
    public Page<MemberEntity> getStateByTwo(Pageable pageable) {
        return memberRepository.findAllByState(State.SECESSION, pageable);
    }

    /**
     * 로그인한 유저 빼고 모든 회원 return
     *
     * @return member entity list(paging)
     */
    public Page<MemberEntity> getAllMemeberByPaging(Long userNo, Pageable pageable) {
        return memberRepository.findAllByUserNoNotLike(userNo, pageable);
    }

    /**
     * 회원가입시 유효성 체크
     *
     * @param errors the errors
     * @return the map
     */
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        // 유효성 검사에 실패한 필드 목록을 받음
        for(FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    /**
     * 회원 id(username) 중복체크
     *
     * @param username the username
     * @return the boolean
     */
    @Transactional(readOnly = true)
    public boolean checkUsernameDuplication(String username) {
        return memberRepository.existsByUsername(username);
    }

    /**
     * 로그인한 유저 빼고 모든 회원 return
     *
     * @return member entity list
     */
    public List<MemberResponseDTO> getAllMemeber(Long userNo) {
        return memberRepository.findAllByUserNoNotLike(userNo).stream().map(MemberResponseDTO::new).collect(Collectors.toList());
    }

    public List<MemberResponseDTO> getMemberListByDeptNo(Long deptNo) {
        return memberRepository.findByDeptEntity_DeptNo(deptNo)
                .stream().map(MemberResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean updateDept(MemberUpdateDeptDto dto){
        try {
            MemberEntity entity = memberRepository.findById(dto.getUserNo())
                    .orElseThrow(() -> new IllegalArgumentException("해당 id가 존재하지 않습니다." + dto.getUserNo()));

            log.info("member entity : {}, dept : {}", entity, entity.getDeptEntity());

            DeptEntity dept = deptRepository
                    .findById(dto.getDeptNo())
                    .orElseThrow(() -> new IllegalArgumentException("해당 id가 존재하지 않습니다." + dto.getDeptNo()));

            log.info("dept entity : {}", dept);

            entity.updateDeptNo(dept);

            return true;
        } catch (Exception e){
            return false;
        }
    }
}
