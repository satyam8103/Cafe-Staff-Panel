package com.cafe.repository;

import com.cafe.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
}
