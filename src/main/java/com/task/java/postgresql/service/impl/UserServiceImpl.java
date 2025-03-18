package com.task.java.postgresql.service.impl;

import com.task.java.postgresql.controller.dto.inbound.AssignRoleRequestDTO;
import com.task.java.postgresql.controller.dto.inbound.UserRequestDTO;
import com.task.java.postgresql.controller.dto.outbound.UserResponseDTO;
import com.task.java.postgresql.model.Role;
import com.task.java.postgresql.model.User;
import com.task.java.postgresql.repository.RoleRepository;
import com.task.java.postgresql.repository.UserRepository;
import com.task.java.postgresql.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword())); // Encrypting Password
        user.setStatus(userRequestDTO.getStatus());

//        Set<Role> roles = roleRepository.findAllById(userRequestDTO.getRoleIds()).stream().collect(Collectors.toSet());
//        user.setRoles(roles);

        userRepository.save(user);
        return mapToUserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToUserResponseDTO).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id).map(this::mapToUserResponseDTO).orElse(null);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        return userRepository.findById(id).map(user -> {
            user.setFirstName(userRequestDTO.getFirstName());
            user.setLastName(userRequestDTO.getLastName());
            user.setEmail(userRequestDTO.getEmail());
            if (userRequestDTO.getPassword() != null && !userRequestDTO.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword())); // Encrypt if updated
            }
            userRepository.save(user);
            return mapToUserResponseDTO(user);
        }).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO assignRolesToUser(Long userId, AssignRoleRequestDTO roleIds) {
        return userRepository.findById(userId).map(user -> {
            Set<Role> roles = roleRepository.findAllById(roleIds.getRoleIds()).stream().collect(Collectors.toSet());
            user.setRoles(roles);
            userRepository.save(user);
            return mapToUserResponseDTO(user);
        }).orElse(null);
    }

    private UserResponseDTO mapToUserResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setStatus(user.getStatus());
        dto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        return dto;
    }
}
