package com.ohalfmoon.firework.persistence;


import com.ohalfmoon.firework.model.AttendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendRepository extends JpaRepository<AttendEntity, Long> {
}
