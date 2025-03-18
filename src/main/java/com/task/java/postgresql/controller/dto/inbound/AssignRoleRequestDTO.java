package com.task.java.postgresql.controller.dto.inbound;

import java.util.Set;

public class AssignRoleRequestDTO {
    private Set<Long> roleIds;

    public Set<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
