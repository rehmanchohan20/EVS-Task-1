package com.task.java.postgresql.controller.dto.inbound;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class PermissionRequestDTO {
    private String name;
    private String description;

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
}

