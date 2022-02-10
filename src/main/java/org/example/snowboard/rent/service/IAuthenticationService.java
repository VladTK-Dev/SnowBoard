package org.example.snowboard.rent.service;

import org.example.snowboard.rent.model.view.RegisterUser;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void register(RegisterUser registerUser);
}
