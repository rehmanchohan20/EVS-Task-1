package com.task.java.postgresql.service.impl;

import com.task.java.postgresql.controller.dto.inbound.AssignPermissionsRequestDTO;
import com.task.java.postgresql.controller.dto.inbound.RoleRequestDTO;
import com.task.java.postgresql.controller.dto.outbound.RoleResponseDTO;
import com.task.java.postgresql.model.Permission;
import com.task.java.postgresql.model.Role;
import com.task.java.postgresql.repository.PermissionRepository;
import com.task.java.postgresql.repository.RoleRepository;
import com.task.java.postgresql.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO) {
        Role role = new Role();
        role.setName(roleRequestDTO.getName());
        role.setDescription(roleRequestDTO.getDescription());
        roleRepository.save(role);
        return mapToRoleResponseDTO(role);
    }

    @Override
    public List<RoleResponseDTO> getAllRoles() {
        return roleRepository.findAll().stream().map(this::mapToRoleResponseDTO).collect(Collectors.toList());
    }

    @Override
    public RoleResponseDTO getRoleById(Long id) {
        return roleRepository.findById(id).map(this::mapToRoleResponseDTO).orElse(null);
    }

    @Override
    public RoleResponseDTO assignPermissionsToRole(Long roleId, AssignPermissionsRequestDTO requestDTO) {
        return roleRepository.findById(roleId).map(role -> {
            Set<Permission> permissions = permissionRepository.findAllById(requestDTO.getPermissionIds()).stream().collect(Collectors.toSet());
            role.setPermissions(permissions);
            roleRepository.save(role);
            return mapToRoleResponseDTO(role);
        }).orElse(null);
    }

    private RoleResponseDTO mapToRoleResponseDTO(Role role) {
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());
        dto.setPermissions(role.getPermissions().stream().map(Permission::getName).collect(Collectors.toSet()));
        return dto;
    }
}

