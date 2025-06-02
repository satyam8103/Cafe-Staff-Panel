package com.cafe.service.Impl;

import com.cafe.Dto.UserDTO;
import com.cafe.entity.Cafe;
import com.cafe.entity.Role;
import com.cafe.entity.User;
import com.cafe.exception.ResourceNotFoundException;
import com.cafe.repository.CafeRepository;
import com.cafe.repository.RoleRepository;
import com.cafe.repository.UserRepository;
import com.cafe.service.Interface.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CafeRepository cafeRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        if (userDTO.getRole() != null && userDTO.getRole().getId() != null) {
            Role role = roleRepository.findById(userDTO.getRole().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Role not found with id: %s", userDTO.getRole().getId())));
            user.setRole(role);
        } else {
            throw new IllegalArgumentException("Role ID must be provided for user creation.");
        }

        if (userDTO.getCafe() != null && userDTO.getCafe().getId() != null) {
            Cafe cafe = cafeRepository.findById(userDTO.getCafe().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Cafe not found with id: %s", userDTO.getCafe().getId())));
            user.setCafe(cafe);
        }
        
        user.setApproved(false);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with id: %s", id)));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO approveUser(Long userId, Long approverId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User to be approved not found with id: %s", userId)));
        User approver = userRepository.findById(approverId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Approver User not found with id: %s", approverId)));

        user.setApproved(true);
        user.setApprovedBy(approver);
        user = userRepository.save(user);

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
