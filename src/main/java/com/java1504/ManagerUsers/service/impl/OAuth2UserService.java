package com.java1504.ManagerUsers.service.impl;

import com.java1504.ManagerUsers.model.Users;
import com.java1504.ManagerUsers.repository.UsersRepository;
import com.java1504.ManagerUsers.service.AuthenticationService;
import com.java1504.ManagerUsers.ultil.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class OAuth2UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationService authenticationService;

    public String getUserAttributes(OAuth2User oAuth2User) {
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        Users users = usersRepository.findUsersByUsername(email);
        if (users == null) {
            Users users1 = new Users();
            users1.setEmail(email);
            users1.setUsername(email);
            users1.setName(name);
            users1.setRole(Role.USER);
            users = users1;
            usersRepository.save(users1);
        }

        return authenticationService.generateToken(users);
    }


}
