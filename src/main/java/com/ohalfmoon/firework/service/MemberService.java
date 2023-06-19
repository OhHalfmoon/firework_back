package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.member.*;
import com.ohalfmoon.firework.model.*;
import com.ohalfmoon.firework.persistence.DeptRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import com.ohalfmoon.firework.persistence.PositionRepository;
import com.ohalfmoon.firework.persistence.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

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
    public Long updatePw(Long userNo, MemberUpdatePwDTO dto) {
        MemberEntity entity = memberRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 id가 존재하지 않습니다." + userNo));
        dto.setPassword(encoder.encode(dto.getPassword()));
        entity.updatePw(dto.getPassword());
        return userNo;
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

    public List<MemberResponseDTO> getMemberList() {
        return memberRepository.findAll()
                .stream().map(MemberResponseDTO::new).collect(Collectors.toList());
    }

    /**
     * state값이 0인 회원 return
     *
     * @return the state by zero
     */
    public List<MemberResponseDTO> getStateByZero() {
        return memberRepository.findAllByState(0)
                .stream().map(MemberResponseDTO::new).collect(Collectors.toList());
    }
}
