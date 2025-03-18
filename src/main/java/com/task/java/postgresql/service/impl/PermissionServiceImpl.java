package com.task.java.postgresql.service.impl;

import com.task.java.postgresql.controller.dto.inbound.PermissionRequestDTO;
import com.task.java.postgresql.controller.dto.outbound.PermissionResponseDTO;
import com.task.java.postgresql.model.Permission;
import com.task.java.postgresql.repository.PermissionRepository;
import com.task.java.postgresql.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public PermissionResponseDTO createPermission(PermissionRequestDTO permissionRequestDTO) {
        Permission permission = new Permission();
        permission.setName(permissionRequestDTO.getName());
        permission.setDescription(permissionRequestDTO.getDescription());
        permissionRepository.save(permission);
        return mapToPermissionResponseDTO(permission);
    }

    @Override
    public List<PermissionResponseDTO> getAllPermissions() {
        return permissionRepository.findAll().stream().map(this::mapToPermissionResponseDTO).collect(Collectors.toList());
    }

    private PermissionResponseDTO mapToPermissionResponseDTO(Permission permission) {
        PermissionResponseDTO dto = new PermissionResponseDTO();
        dto.setId(permission.getId());
        dto.setName(permission.getName());
        dto.setDescription(permission.getDescription());
        return dto;
    }
}

