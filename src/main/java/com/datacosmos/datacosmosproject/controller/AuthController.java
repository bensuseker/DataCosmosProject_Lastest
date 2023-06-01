package com.datacosmos.datacosmosproject.controller;

import com.datacosmos.datacosmosproject.Dto.UserDto;
import com.datacosmos.datacosmosproject.entities.User;
import com.datacosmos.datacosmosproject.repository.datasetsRepository;
import com.datacosmos.datacosmosproject.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {

    private final IUserService userService;
    private final datasetsRepository datasetRepo;

    @Autowired
    public AuthController(IUserService userService, datasetsRepository datasetRepo) {
        this.userService = userService;
        this.datasetRepo = datasetRepo;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // Handler method to display the login form
    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        // Check if a user with the same email already exists
        User existingUser = userService.findByEmail(user.getEmail());

        if (existingUser != null) {
            // If there is an existing user with the same email, reject the registration
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            // If there are validation errors, return to the registration form with error messages
            model.addAttribute("user", user);
            return "register";
        }
        // Save the user if there are no errors
        userService.saveUser(user);

        // Redirect to the registration form with a success message
        return "redirect:/register?success";
    }



}
