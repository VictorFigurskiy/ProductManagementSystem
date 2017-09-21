package com.product.system;

import com.product.system.configuration.ModelConfiguration;
import com.product.system.entity.ProductEntity;
import com.product.system.entity.UserEntity;
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
            UserEntity userEntity = userService.getById(-1);

            System.out.println(userEntity);

            if (userEntity == null) {
                userEntity = new UserEntity();
                userEntity.setFirstName("John");
                userEntity.setLastName("Cooper");
                userEntity.setPassword("qwerty");
                userEntity.setEmail("111@gmail.com");
                userService.save(userEntity);
            }

            UserEntity userEntity1 = new UserEntity();
            userEntity1.setFirstName("Petya");
            userEntity1.setLastName("Carpov");
            userEntity1.setPassword("111");
            userEntity1.setEmail("1@gmail.com");
            userService.save(userEntity1);

            System.out.println(userService.getAll());
            userEntity.setAdmin(true);
            userService.update(userEntity);
            System.out.println(userService.getAll());
//        userService.remove(User1);
            System.out.println(userService.getAll());
            UserEntity byEmail = userService.getByEmail("victor@gmail.com");

            System.out.println("This is what I'm looking for" + byEmail);

            ProductService productService = ctx.getBean(ProductService.class);
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName("Phone");
            productEntity.setDescriptions("Iphone 7");
            productEntity.setManufacturer("Apple");
            productEntity.setPrice(new BigDecimal(800.50));

            productService.save(productEntity);
            System.out.println(productService.getById(1));
            productService.save(productEntity);

            ProductEntity productEntityById = productService.getById(2);
            productEntityById.setManufacturer("ChinaPhoneCopy");
            productService.update(productEntityById);

            System.out.println(productService.getAll());

            ctx.close();
        }
    }
}

class Test{
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ModelConfiguration.class)){
            UserService userService = context.getBean(UserService.class);
            UserEntity userEntityVic = userService.getById(1);
            UserEntity userEntitySerg = userService.getById(2);

            System.out.println(userEntitySerg);
        }
    }
}
