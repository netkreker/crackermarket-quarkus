package com.netkreker.crackermarket;

import com.netkreker.crackermarket.models.user.User;
import com.netkreker.crackermarket.services.UserService;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class Startup {

    @Inject
    UserService userService;
    @Transactional
    public void createAdmin(@Observes StartupEvent evt) {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@mail.com");

        if(userService.findByUsername(admin.getUsername()) == null) {
            userService.persist(admin);
        }

    }
}
