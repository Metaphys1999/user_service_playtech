package com.user.service.jpql;

import com.user.service.entity.User;
import com.user.service.role.Role;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestUserJPQL {

    private final static Logger logger = LoggerFactory.getLogger(TestUserJPQL.class);
    String message = "Prueba";

    @Autowired
    private EntityManager entityManager;

    /* Inversion de Control, BeforeEach - BeforeAll - AfterAll - AfterEach*/

    @BeforeEach
    public void beforeEach() {
        logger.info(message);
        assertNotNull(entityManager, "El entityManager es nulo");
    }

    /* JPQL */

    @Test
    public void selectWhereActive() {
        logger.info(message);
        String jpql = "SELECT user FROM User user WHERE user.userStatus = 'ACTIVE' ORDER BY user.firstname";
        List<User> userList = entityManager.createQuery(jpql, User.class).getResultList();

        userList.forEach(user -> {
            logger.info(user.getFirstname());
            logger.info("" + user.getRole());
        });
    }

    @Test
    public void selectWhereParam() {
        logger.info(message);
        String jpql = "SELECT user FROM User user WHERE user.userStatus =: status AND user.firstname =: name"; //Consultas (AND) where con uso de parametros
        List<User> userList = entityManager.
                createQuery(jpql, User.class).
                setParameter("status", Role.ADMIN).
                setParameter("name", "Alejo").
                getResultList();

        userList.forEach(user -> {
            logger.info(user.getFirstname());
            logger.info("" + user.getRole());
        });
    }

    @Test
    public void selectLike() {
        logger.info(message);
        String jpql = "SELECT user FROM User user WHERE user.firstname LIKE 'Juan%'"; //Si se va a buscar un caracter en cualquier parte de los campos se usa %a% DemoController %am% (Por ejemplo)
        List<User> userList = entityManager.createQuery(jpql, User.class).getResultList();

        userList.forEach(user -> {
            logger.info(user.getFirstname());
        });
    }

    @Test
    public void selectAll() {
        logger.info(message);
        String jpql = "SELECT user FROM User user";
        List<User> userList = entityManager.createQuery(jpql, User.class).getResultList(); /*Retorna una lista de
																									Customer que hay en la BD*/
        userList.forEach(user -> { // Esta es una forma de recorrer el arreglo con programacion funcional (Lambda)
            logger.info(user.getFirstname());
            logger.info("" + user.getRole());
        });
    }

}