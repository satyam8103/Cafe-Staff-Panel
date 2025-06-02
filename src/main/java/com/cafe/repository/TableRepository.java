package com.cafe.repository;



import com.cafe.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
    List<TableEntity> findByCafeId(Long cafeId);
}
