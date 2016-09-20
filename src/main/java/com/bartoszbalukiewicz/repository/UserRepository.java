package com.bartoszbalukiewicz.repository;

import com.bartoszbalukiewicz.model.Role;
import com.bartoszbalukiewicz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bartek on 18.09.2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
