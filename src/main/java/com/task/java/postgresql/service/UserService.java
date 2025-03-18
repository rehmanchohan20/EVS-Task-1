package com.task.java.postgresql.service;

import com.task.java.postgresql.controller.dto.inbound.AssignRoleRequestDTO;
import com.task.java.postgresql.controller.dto.inbound.UserRequestDTO;
import com.task.java.postgresql.controller.dto.outbound.UserResponseDTO;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    void deleteUser(Long id);
    UserResponseDTO assignRolesToUser(Long userId, AssignRoleRequestDTO roleIds);
}

