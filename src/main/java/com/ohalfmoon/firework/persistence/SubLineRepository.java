package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.SubLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubLineRepository extends JpaRepository<SubLineEntity, Long> {
}
