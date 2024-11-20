package com.example.opensource_blog.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserAccount,String> {


    Optional<UserAccount> findByUserId(String userId);

    boolean existsByUserId(String userId);

    boolean existsByUsername(String username);
}
