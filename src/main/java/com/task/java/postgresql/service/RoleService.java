package com.task.java.postgresql.service;

import com.task.java.postgresql.controller.dto.inbound.AssignPermissionsRequestDTO;
import com.task.java.postgresql.controller.dto.inbound.RoleRequestDTO;
import com.task.java.postgresql.controller.dto.outbound.RoleResponseDTO;

import java.util.List;
import java.util.Set;

public interface RoleService {
    RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO);
    List<RoleResponseDTO> getAllRoles();
    RoleResponseDTO getRoleById(Long id);
    public RoleResponseDTO assignPermissionsToRole(Long roleId, AssignPermissionsRequestDTO requestDTO);
}
