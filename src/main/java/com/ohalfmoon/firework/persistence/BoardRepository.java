package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {


}
