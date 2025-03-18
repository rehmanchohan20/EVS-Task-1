package com.task.java.postgresql.controller.dto.inbound;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AssignPermissionsRequestDTO {
    private Set<Long> permissionIds;

    public Set<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(Set<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
}