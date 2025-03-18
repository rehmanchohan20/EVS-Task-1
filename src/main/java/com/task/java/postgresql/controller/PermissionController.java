package com.task.java.postgresql.controller;

import com.task.java.postgresql.controller.dto.inbound.PermissionRequestDTO;
import com.task.java.postgresql.controller.dto.outbound.PermissionResponseDTO;
import com.task.java.postgresql.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public ResponseEntity<PermissionResponseDTO> createPermission(@RequestBody PermissionRequestDTO permissionRequestDTO) {
        return ResponseEntity.ok(permissionService.createPermission(permissionRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<PermissionResponseDTO>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }
}

