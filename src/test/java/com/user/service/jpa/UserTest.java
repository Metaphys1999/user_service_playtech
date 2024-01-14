package com.user.service.jpa;

import com.user.service.entity.User;
import com.user.service.role.Role;
import jakarta.persistence.EntityManager;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false) //Inversión de Control
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTest {

    private final static int userId = 1;
    private final static Logger log = LoggerFactory.getLogger(UserTest.class);

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional //Inversión de Control
    @Order(1)
    void save() {
        assertNotNull(entityManager, "El entityManager es nulo");
        User user = entityManager.find(User.class, userId);

        assertNull(user, "El cliente con email: " + userId + " ya existe.");

        user = new User();
        user.setFirstName("Alejandro");
        user.setLastName("Aguado");

        Date dateOfBirth = Date.valueOf("1999-06-19");
        user.setDateBirth(dateOfBirth);

        user.setAddress("Avenida");
        user.setPassword("123");
        user.setPhone("987");
        user.setEmail("prueba@gmail.com");
        user.setRole(Role.ADMIN);

        entityManager.persist(user);
    }

    @Test
    @Transactional
    @Order(2)
    void findById() {
        assertNotNull(entityManager, "El entityManager es nulo");
        User user = entityManager.find(User.class, userId);

        assertNotNull(user, "El cliente con email: " + userId + " no existe.");

        log.info("Buscando al usuario con ID: " +
                user.getUserId() +
                " nos damos cuenta que su nombre es: " +
                user.getFirstName());
    }

    @Test
    @Transactional
    @Order(3)
    void update() {
        assertNotNull(entityManager, "El entityManager es nulo");
        User user = entityManager.find(User.class, userId);

        assertNotNull(user, "El cliente con email: " + userId + " no existe.");

        user.setFirstName("Mathew");

        entityManager.merge(user);

        log.info("Buscando al usuario con ID: " +
                user.getUserId() +
                " nos damos cuenta que su nombre es: " +
                user.getFirstName());
    }

    @Test
    @Transactional
    @Order(4)
    void remove() {
        assertNotNull(entityManager, "El entityManager es nulo");
        User user = entityManager.find(User.class, userId);

        assertNotNull(user, "El cliente con email: " + userId + " no existe.");

        entityManager.remove(user);
    }
}
