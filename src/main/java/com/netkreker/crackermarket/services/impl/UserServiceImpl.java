package com.netkreker.crackermarket.services.impl;


import com.netkreker.crackermarket.models.user.Role;
import com.netkreker.crackermarket.models.user.Status;
import com.netkreker.crackermarket.models.user.User;
import com.netkreker.crackermarket.repository.UserRepository;
import com.netkreker.crackermarket.services.UserService;
import io.quarkus.elytron.security.common.BcryptUtil;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @ConfigProperty(name = "salt")
    private String salt;

    @Inject
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll().list();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(UUID.fromString(id));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void persist(User user) {

        setEncodedPassword(user);

        if(user.getUsername().equals("admin")) {
            user.setRole(Role.ROLE_ADMIN);
        } else user.setRole(Role.ROLE_SIMPLE_USER);

        user.setStatus(Status.ACTIVE);
        userRepository.persist(user);
    }

    private void setEncodedPassword(User user) {
        user.setPassword(BcryptUtil.bcryptHash(user.getPassword()));
    }
}
