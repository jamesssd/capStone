package com.jamesdizon.ecommercestore.api.controller.auth;

import com.jamesdizon.ecommercestore.api.model.LoginBody;
import com.jamesdizon.ecommercestore.api.model.LoginResponse;
import com.jamesdizon.ecommercestore.api.model.RegistrationBody;
import com.jamesdizon.ecommercestore.exception.UserCheck;
import com.jamesdizon.ecommercestore.model.LocalUser;
import com.jamesdizon.ecommercestore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody){
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserCheck ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
        String jwt = userService.loginUser(loginBody);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/me")
    public LocalUser getLogInUserProfile(@AuthenticationPrincipal LocalUser user){
        return user;
    }

}
