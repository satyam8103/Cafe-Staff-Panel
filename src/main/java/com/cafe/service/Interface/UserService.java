package com.cafe.service.Interface;

import com.cafe.Dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO approveUser(Long userId, Long approverId);
    void deleteUser(Long id);
}
