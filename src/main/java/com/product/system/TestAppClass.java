package com.product.system;

import com.product.system.configuration.ModelConfiguration;
import com.product.system.entity.Product;
import com.product.system.entity.User;
import com.product.system.services.ProductService;
import com.product.system.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

/**
 * Created by Sonikb on 08.08.2017.
 */
public class TestAppClass {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModelConfiguration.class)) {
            UserService userService = ctx.getBean(UserService.class);
            User user = userService.getById(-1);

            System.out.println(user);

            if (user == null) {
                user = new User();
                user.setFirstName("John");
                user.setLastName("Cooper");
                user.setPassword("qwerty");
                user.setEmail("111@gmail.com");
                userService.save(user);
            }

            User user1 = new User();
            user1.setFirstName("Petya");
            user1.setLastName("Carpov");
            user1.setPassword("111");
            user1.setEmail("1@gmail.com");
            userService.save(user1);

            System.out.println(userService.getAll());
            user.setAdmin(true);
            userService.update(user);
            System.out.println(userService.getAll());
//        userService.remove(User1);
            System.out.println(userService.getAll());
            User byEmail = userService.getByEmail("victor@gmail.com");

            System.out.println("This is what I'm looking for" + byEmail);

            ProductService productService = ctx.getBean(ProductService.class);
            Product product = new Product();
            product.setName("Phone");
            product.setDescriptions("Iphone 7");
            product.setManufacturer("Apple");
            product.setPrice(new BigDecimal(800.50));

            productService.save(product);
            System.out.println(productService.getById(1));
            productService.save(product);

            Product productById = productService.getById(2);
            productById.setManufacturer("ChinaPhoneCopy");
            productService.update(productById);

            System.out.println(productService.getAll());

            ctx.close();
        }
    }
}

class Test{
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ModelConfiguration.class)){
            UserService userService = context.getBean(UserService.class);
            User userVic = userService.getById(1);
            User userSerg = userService.getById(2);

            System.out.println(userSerg);
        }
    }
}
