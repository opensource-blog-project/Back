package com.example.opensource_blog.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount,String> {


    Optional<UserAccount> findByUsername(String username);

    boolean existsByUsername(String username);
}
