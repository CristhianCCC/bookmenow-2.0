package com.bookmenow.user.repository;
import com.bookmenow.user.model.User;
import com.bookmenow.user.model.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail (String email);

    Optional<User> findByEmail (String email);

    //boolean existsByRole (UserRoles role);

}
