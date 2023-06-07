package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.member.MemberDTO;
import com.ohalfmoon.firework.dto.role.RoleDTO;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.PositionEntity;
import com.ohalfmoon.firework.model.RoleEntity;
import com.ohalfmoon.firework.persistence.DeptRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import com.ohalfmoon.firework.persistence.PositionRepository;
import com.ohalfmoon.firework.persistence.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public RoleEntity register(MemberDTO memberDTO, RoleDTO roleDTO) {
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


    public MemberEntity login (final String username, final String password) {
        return memberRepository.findByUsernameAndPassword(username, password);
    }

    public MemberEntity get (final String username) {
        return memberRepository.findByUsername(username);
    }
}
