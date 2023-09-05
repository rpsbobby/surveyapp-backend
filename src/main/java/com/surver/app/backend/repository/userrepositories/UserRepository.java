package com.surver.app.backend.repository.userrepositories;

import com.surver.app.backend.entity.userentities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
