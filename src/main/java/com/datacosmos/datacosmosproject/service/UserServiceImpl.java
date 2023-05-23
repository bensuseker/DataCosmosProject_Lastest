package com.datacosmos.datacosmosproject.service;

import com.datacosmos.datacosmosproject.Dto.UserDto;
import com.datacosmos.datacosmosproject.entities.Role;
import com.datacosmos.datacosmosproject.entities.User;
import com.datacosmos.datacosmosproject.repository.RoleRepository;
import com.datacosmos.datacosmosproject.repository.userRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private userRepository userRepo;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(userRepository userRepo, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepo.save(user);

    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        String[] name = user.getUsername().split(" ");
        userDto.setUsername(name[0]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}







