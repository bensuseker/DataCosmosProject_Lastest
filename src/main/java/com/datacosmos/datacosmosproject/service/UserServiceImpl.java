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

/**
 * The UserServiceImpl class provides the implementation of the IUserService interface.
 * It handles the business logic for managing users.
 * It interacts with the user repository and role repository for user-related operations.
 */
@Service
public class UserServiceImpl implements IUserService {

    private userRepository userRepo;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    /**
     * Constructs a new UserServiceImpl with the specified dependencies.
     *
     * @param userRepo         The user repository.
     * @param roleRepository   The role repository.
     * @param passwordEncoder  The password encoder for encrypting passwords.
     */
    public UserServiceImpl(userRepository userRepo, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Saves a new user based on the provided UserDto object.
     * The password is encrypted using the password encoder.
     * The user is assigned the "ROLE_ADMIN" role if it exists, otherwise a new role is created.
     *
     * @param userDto The UserDto object containing the user details.
     */
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

    /**
     * Finds a user by email.
     *
     * @param email The email of the user.
     * @return The User object if found, null otherwise.
     */
    @Override
    public User findByEmail(String email) {
        return null;
    }

    /**
     * Retrieves a list of all users and converts them to UserDto objects.
     *
     * @return A list of UserDto objects representing all users.
     */
    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    /**
     * Converts a User entity to a UserDto object.
     *
     * @param user The User entity.
     * @return The UserDto object.
     */
    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        String[] name = user.getUsername().split(" ");
        userDto.setUsername(name[0]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    /**
     * Checks if the "ROLE_ADMIN" role exists in the role repository.
     * If not, creates a new role with the name "ROLE_ADMIN" and saves it in the repository.
     *
     * @return The Role object representing the "ROLE_ADMIN" role.
     */
    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}







