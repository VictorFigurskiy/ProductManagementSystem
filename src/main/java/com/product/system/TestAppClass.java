package com.product.system;

import com.product.system.configuration.HibernateConfig;
import com.product.system.entity.User;
import com.product.system.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sonikb on 08.08.2017.
 */
public class TestAppClass {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateConfig.class)) {
            UserService userService = ctx.getBean(UserService.class);
            User user = userService.getById(1);

            System.out.println(user);

            if (user == null) {
                user = new User();
                user.setFirstName("John");
                user.setLastName("Cooper");
                user.setPassword("qwerty");
                userService.save(user);
            }

            User user1 = new User();
            user1.setFirstName("Petya");
            user1.setLastName("Carpov");
            user1.setPassword("111");
            userService.save(user1);

            System.out.println(userService.getAll());
            user.setLastName("Dowson");
            userService.update(user);
            System.out.println(userService.getAll());
//        userService.remove(User1);
            System.out.println(userService.getAll());

//            ctx.close();
        }
    }
}
