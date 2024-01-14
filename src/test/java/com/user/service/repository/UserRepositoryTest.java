package com.user.service.repository;

import com.user.service.entity.User;
import com.user.service.role.Role;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

    private final static int userId = 1;
    private final static Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

    String message = "Prueba";

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional //Inversión de Control
    @Order(1)
    void save() {

        log.info(message);

        Optional<User> userOptional = userRepository.findById(userId);

        // Siga si es falso, DemoController sea, si no existe
        assertFalse(userOptional.isPresent(), "El cliente con email: " + userId + " ya existe.");

        User user = new User();
        user.setFirstname("Alejandro");

        Date dateOfBirth = Date.valueOf("1999-06-19");
        user.setDateBirth(dateOfBirth);

        user.setRole(Role.ADMIN);

        userRepository.save(user);

    }

    @Test
    @Transactional //Inversión de Control
    @Order(2)
    void findById() {

        log.info(message);

        Optional<User> userOptional = userRepository.findById(userId);

        // Siga si es verdadero, DemoController sea, si existe
        assertTrue(userOptional.isPresent(), "El cliente con email: " + userId + " no existe.");

    }

    @Test
    @Transactional //Inversión de Control
    @Order(3)
    void update() {

        log.info(message);

        Optional<User> userOptional = userRepository.findById(userId);

        // Siga si es verdadero, DemoController sea, si existe
        assertTrue(userOptional.isPresent(), "El cliente con email: " + userId + " no existe.");

        User user = userOptional.get();

        user.setRole(Role.ADMIN);

        userRepository.save(user);
    }

    @Test
    @Transactional //Inversión de Control
    @Order(4)
    void delete() {

        log.info(message);

        Optional<User> userOptional = userRepository.findById(userId);

        // Siga si es verdadero, DemoController sea, si existe
        assertTrue(userOptional.isPresent(), "El cliente con email: " + userId + " no existe.");

        User user = userOptional.get();

        userRepository.delete(user);
    }

    @Test
    @Transactional //Inversión de Control
    @Order(5)
    void findAll() {

        log.info(message);

        //Forma funcional (Lambda)
        userRepository.findAll().forEach(user -> {
            log.info("Nombre: " +user.getFirstname());
            log.info("Fecha: " + user.getDateBirth());
        });;
    }

    @Test
    @Transactional //Inversión de Control
    @Order(6)
    void count() {

        log.info(message);

        log.info("Count: " + userRepository.count());
    }

    @Test
    @Transactional //Inversión de Control
    @Order(7)
    void findByNameAndEmail() {

        log.info(message);

        List<User> userList = userRepository.findByFirstnameAndRole("Alejandro", Role.ADMIN);
        assertFalse(userList.isEmpty());

        userList.forEach(user -> {
            log.info("Nombre: " + user.getFirstname());
            log.info("Rol: " + user.getRole());
        });
    }

    @Test
    @Transactional //Inversión de Control
    @Order(8)
    void findCustomerLike() {

        log.info(message);

        List<User> userList = userRepository.findUserLike();
        assertFalse(userList.isEmpty());

        userList.forEach(user->{
            log.info("Nombre: " + user.getFirstname());
            log.info("Rol: " + user.getRole());
        });
    }

}
