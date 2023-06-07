package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.form.DeptDTO;
import com.ohalfmoon.firework.dto.form.MemberDTO;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.PositionEntity;
import com.ohalfmoon.firework.persistence.DeptRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import com.ohalfmoon.firework.persistence.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private PositionRepository positionRepository;

//    private De

//    public MemberEntity register(MemberEntity memberEntity) {
//        return memberRepository.save(memberEntity);
//    }
    public MemberEntity register(MemberDTO memberDTO) {
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

    public MemberEntity login (final String username, final String password) {
        return memberRepository.findByUsernameAndPassword(username, password);
    }

    public MemberEntity get (final String username) {
        return memberRepository.findByUsername(username);
    }
}
