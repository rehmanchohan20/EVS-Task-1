package com.task.java.postgresql.controller;

import com.task.java.postgresql.controller.dto.inbound.AssignPermissionsRequestDTO;
import com.task.java.postgresql.controller.dto.inbound.RoleRequestDTO;
import com.task.java.postgresql.controller.dto.outbound.RoleResponseDTO;
import com.task.java.postgresql.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        return ResponseEntity.ok(roleService.createRole(roleRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PostMapping("/{roleId}/permissions")
    public ResponseEntity<RoleResponseDTO> assignPermissionsToRole(
            @PathVariable Long roleId,
            @RequestBody AssignPermissionsRequestDTO requestDTO) {
        RoleResponseDTO response = roleService.assignPermissionsToRole(roleId, requestDTO);
        return ResponseEntity.ok(response);
    }
}

