package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.member.*;
import com.ohalfmoon.firework.model.*;
import com.ohalfmoon.firework.persistence.DeptRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import com.ohalfmoon.firework.persistence.PositionRepository;
import com.ohalfmoon.firework.persistence.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * 회원가입 기능
     *
     * @param memberDTO the member dto
     * @return the role entity
     */
    @Transactional // springboot
    public RoleEntity register(MemberDTO memberDTO) {
        MemberEntity entity = memberDTO.toEntity();
        DeptEntity byId = deptRepository
                .findById(memberDTO.getDeptNo())
                .orElseThrow(() -> new IllegalArgumentException(""));
        entity.updateDeptNo(byId);

        PositionEntity byId2 = positionRepository
                .findById(memberDTO.getPositionNo())
                .orElseThrow(() -> new IllegalArgumentException(""));
        entity.updatePositionNo(byId2);

        memberRepository.save(entity);

        RoleEntity entityBuilder = RoleEntity.builder()
                .roleName(Role.GUEST.getKey())
                .build();

        return roleRepository.save(entityBuilder);

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

        entity.updatePw(dto.getPassword());

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

        if(entity != null && entity.getPassword().equals(memberLoginDTO.getPassword())) {
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
}
