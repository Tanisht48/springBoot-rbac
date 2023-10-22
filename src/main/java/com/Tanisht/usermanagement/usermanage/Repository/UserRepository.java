package com.Tanisht.usermanagement.usermanage.Repository;

import com.Tanisht.usermanagement.usermanage.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);

    User findFirstByUserEmail(String email);
}

