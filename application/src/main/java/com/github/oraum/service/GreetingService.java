package com.github.oraum.service;

import com.github.oraum.persistence.dao.UserDao;
import com.github.oraum.persistence.entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class GreetingService {

    private static final String GREETING_STRING = "Hello %s!";

    @Inject
    UserDao dao;

    public String greetUser(String username) {
        return String.format(GREETING_STRING, username);
    }

    public String greetUserWithId(Long id) {
        User user = dao.getUserById(id);
        return String.format(GREETING_STRING, user.getName());
    }

    public String greetAllUsers() {
        List<User> users = dao.getAllUsers();
        return users.stream().map(user -> String.format(GREETING_STRING, user.getName())).collect(Collectors.joining("\n"));
    }
}
