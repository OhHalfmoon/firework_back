package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.member.*;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.PositionEntity;
import com.ohalfmoon.firework.model.RoleEntity;
import com.ohalfmoon.firework.persistence.DeptRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import com.ohalfmoon.firework.persistence.PositionRepository;
import com.ohalfmoon.firework.persistence.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                .memberEntity(entity)
                .authName("GUEST")
                .build();

        return roleRepository.save(entityBuilder);

}

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
    @Transactional
    public Long updatePw(Long userNo, MemberUpdatePwDTO dto) {
        MemberEntity entity = memberRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 id가 존재하지 않습니다." + userNo));

        entity.updatePw(dto.getPassword());

        return userNo;

    }

    public MemberResponseDTO login (MemberLoginDTO memberLoginDTO) {
        MemberEntity entity = memberRepository.findByUsername(memberLoginDTO.getUsername());

        if(entity != null && entity.getPassword().equals(memberLoginDTO.getPassword())) {
            return new MemberResponseDTO(entity);
        }
        return null;
    }

    public MemberEntity get(final String username) {
        return memberRepository.findByUsername(username);
    }
}
