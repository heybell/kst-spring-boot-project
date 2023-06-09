package com.bell.kst.repository;

import com.bell.kst.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByEmailAndNameIsNotNullOrNameAndEmailIsNotNull(String email, String name);
}
