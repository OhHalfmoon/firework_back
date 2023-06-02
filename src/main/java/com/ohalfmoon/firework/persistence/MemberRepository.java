package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;
import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
}
