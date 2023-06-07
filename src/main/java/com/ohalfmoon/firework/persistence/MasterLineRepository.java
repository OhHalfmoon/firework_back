package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.MasterLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterLineRepository extends JpaRepository<MasterLineEntity, Long> {
}
