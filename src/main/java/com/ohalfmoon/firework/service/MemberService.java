package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.MemberDTO;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public MemberEntity register(MemberEntity memberEntity) {
        return memberRepository.save(memberEntity);
    }

    public MemberEntity login (final String username, final String password) {
        return memberRepository.findByUsernameAndPassword(username, password);
    }
}
