package com.cafe.service.Impl;

import com.cafe.Dto.CafeDTO;
import com.cafe.entity.Cafe;
import com.cafe.exception.ResourceNotFoundException;
import com.cafe.repository.CafeRepository;
import com.cafe.service.Interface.CafeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CafeServiceImpl implements CafeService {
    @Autowired
    private CafeRepository cafeRepository;
    @Autowired private ModelMapper modelMapper;

    @Override
    public CafeDTO createCafe(CafeDTO dto) {
        Cafe cafe = modelMapper.map(dto, Cafe.class);
        return modelMapper.map(cafeRepository.save(cafe), CafeDTO.class);
    }

    @Override
    public CafeDTO getCafeById(Long id) {
        Cafe cafe = cafeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cafe not found with id: " + id));
        return modelMapper.map(cafe, CafeDTO.class);
    }

    @Override
    public List<CafeDTO> getAllCafes() {
        return cafeRepository.findAll()
                .stream()
                .map(c -> modelMapper.map(c, CafeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CafeDTO updateCafe(Long id, CafeDTO dto) {
        Cafe cafe = cafeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cafe not found with id: " + id));
        cafe.setName(dto.getName());
        cafe.setLocation(dto.getLocation());
        return modelMapper.map(cafeRepository.save(cafe), CafeDTO.class);
    }

    @Override
    public void deleteCafe(Long id) {
        cafeRepository.deleteById(id);
    }
}
