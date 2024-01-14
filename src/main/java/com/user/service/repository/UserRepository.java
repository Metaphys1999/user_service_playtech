package com.user.service.repository;

import com.user.service.entity.User;
import com.user.service.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Integer> {

    Optional<User> findByPhone(String phone);

    List<User> findByFirstnameAndRole(String firstname, Role role);

    @Query("SELECT user FROM User user WHERE user.firstname LIKE 'Ale%'")
    List<User> findUserLike();
}
