package com.task.java.postgresql.service;

import com.task.java.postgresql.controller.dto.inbound.PermissionRequestDTO;
import com.task.java.postgresql.controller.dto.outbound.PermissionResponseDTO;

import java.util.List;

public interface PermissionService {
    PermissionResponseDTO createPermission(PermissionRequestDTO permissionRequestDTO);
    List<PermissionResponseDTO> getAllPermissions();
}
