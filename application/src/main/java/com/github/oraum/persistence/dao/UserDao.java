package com.github.oraum.persistence.dao;

import com.github.oraum.persistence.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserDao {

    @Inject
    EntityManager entityManager;

    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    public List<User> getAllUsers() {
        return entityManager.createNamedQuery("User.getAll", User.class).getResultList();
    }

    public User persistUser(String userName) {
        User user = new User();
        user.setName(userName);
        entityManager.persist(user);
        return user;
    }
}
