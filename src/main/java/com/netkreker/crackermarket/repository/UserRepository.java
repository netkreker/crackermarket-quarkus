package com.netkreker.crackermarket.repository;

import com.netkreker.crackermarket.models.user.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public User findById(UUID id) {
        return find("id", id).firstResult();
    }

    public User findByUsername(String username) {
        return find("username", username).firstResult();
    }

    public User findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public void delete(User user) {
        delete(user);
    }


}
