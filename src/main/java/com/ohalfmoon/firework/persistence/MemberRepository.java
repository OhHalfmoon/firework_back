package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.dto.member.MemberLoginDTO;
import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
//    MemberEntity findByUsernameAndPassword();

    MemberEntity findByUsername(String username);

    List<MemberEntity> findAllByState(int state);

    List<MemberEntity> findAllByUserNoNotLike(Long userNo);
}
