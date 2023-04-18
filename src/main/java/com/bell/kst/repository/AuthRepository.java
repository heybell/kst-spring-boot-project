package com.bell.kst.repository;

import com.bell.kst.entity.Auth;
import com.bell.kst.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Integer> {
    List<Auth> findByRole(Role role);
}
