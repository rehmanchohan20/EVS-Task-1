package com.task.java.postgresql.controller.dto.outbound;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;


public class RoleResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Set<String> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}

