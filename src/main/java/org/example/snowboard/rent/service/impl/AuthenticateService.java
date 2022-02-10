package org.example.snowboard.rent.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.snowboard.rent.database.IUserDAO;
import org.example.snowboard.rent.exceptions.LoginAlreadyUseException;
import org.example.snowboard.rent.model.User;
import org.example.snowboard.rent.model.view.RegisterUser;
import org.example.snowboard.rent.service.IAuthenticationService;
import org.example.snowboard.rent.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticateService implements IAuthenticationService {

    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> user = this.userDAO.getUserByLogin(login);

        if(user.isEmpty() ||
                !user.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            return;
        }
        this.sessionObject.setUser(user.get());
    }

    @Override
    public void register(RegisterUser registerUser) {
        Optional<User> userBox = this.userDAO.getUserByLogin(registerUser.getLogin());

        if(userBox.isPresent()) {
            throw new LoginAlreadyUseException();
        }

        registerUser.setPassword(DigestUtils.md5Hex(registerUser.getPassword()));

        User user = new User();
        user.setLogin(registerUser.getLogin());
        user.setPassword(registerUser.getPassword());
        user.setSurName(registerUser.getSurName());
        user.setName(registerUser.getName());

        this.userDAO.addUser(user);
    }
}
