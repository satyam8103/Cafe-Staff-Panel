package com.cafe.service.Interface;

import com.cafe.Dto.CafeDTO;

import java.util.List;

public interface CafeService {
    CafeDTO createCafe(CafeDTO cafeDTO);
    CafeDTO getCafeById(Long id);
    List<CafeDTO> getAllCafes();
    CafeDTO updateCafe(Long id, CafeDTO cafeDTO);
    void deleteCafe(Long id);
}

