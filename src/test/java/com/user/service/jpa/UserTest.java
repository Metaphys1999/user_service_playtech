package com.user.service.jpa;

import com.user.service.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Rollback(value = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTest {

    private final static String phone = "3046033248";

    private final static Logger logger = LoggerFactory.getLogger(UserTest.class);

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @Transactional
    @Order(1)
    void create() {
        assertNotNull(entityManager, "El entityManager es nulo");
        User user = entityManager.find(User.class, phone);

        assertNull(user, "El usuario con teléfono: " + phone + " ya existe.");

        user = new User();
        user.setUserId(1);
        user.setFirstName("Juan");
        user.setLastName("Gil");

        Date dateOfBirth = Date.valueOf("1999-06-19");
        user.setDateBirth(dateOfBirth);

        user.setAddress("Avenida siempre viva");
        user.setPassword("123");
        user.setPhone(phone);
        user.setEmail("gil@gmail.com");

        entityManager.persist(user);
    }

    @Test
    @Transactional
    @Order(2)
    void findById() {
        assertNotNull(entityManager, "El entityManager es nulo");
        User user = entityManager.find(User.class, phone);

        assertNotNull(user, "El usuario con teléfono: " + phone + " no existe.");

        logger.info(user.getEmail());
    }

    @Test
    @Transactional
    @Order(3)
    void update() {
        assertNotNull(entityManager, "El entityManager es nulo");
        User user = entityManager.find(User.class, phone);

        assertNotNull(user, "El usuario con teléfono: " + phone + " no existe.");

        user.setPassword("SECRETA");

        entityManager.merge(user);
    }

    void remove() {
        assertNotNull(entityManager, "El entityManager es nulo");
        User user = entityManager.find(User.class, phone);

        assertNotNull(user, "El usuario con teléfono: " + phone + " ya existe.");

        entityManager.remove(user);
    }
}
