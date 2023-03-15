package com.jamesdizon.ecommercestore.service;

import com.jamesdizon.ecommercestore.api.model.LoginBody;
import com.jamesdizon.ecommercestore.api.model.RegistrationBody;
import com.jamesdizon.ecommercestore.exception.UserCheck;
import com.jamesdizon.ecommercestore.model.LocalUser;
import com.jamesdizon.ecommercestore.model.dao.LocalUserDAO;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private LocalUserDAO localUserDAO;
    private EncryptionService encryptionService;

    private JWTService jwtService;


    public UserService(LocalUserDAO localUserDAO, EncryptionService encryptionService, JWTService jwtService) {
        this.localUserDAO = localUserDAO;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    public LocalUser registerUser(RegistrationBody registrationBody) throws UserCheck {
        if (localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
                || localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserCheck();
        }
        LocalUser user = new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setUsername(registrationBody.getUsername());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        return localUserDAO.save(user);
    }

    public String loginUser(LoginBody loginBody) {
        Optional<LocalUser> opUser = localUserDAO.findByUsernameIgnoreCase(loginBody.getUsername());
        if (opUser.isPresent()) {
            LocalUser user = opUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }

}
