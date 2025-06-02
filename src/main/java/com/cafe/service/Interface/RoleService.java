package com.cafe.service.Interface;

import com.cafe.Dto.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO);
    List<RoleDTO> getAllRoles();
}
