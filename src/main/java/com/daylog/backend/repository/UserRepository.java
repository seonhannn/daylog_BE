package com.daylog.backend.repository;

import com.daylog.backend.entity.User;
import com.daylog.backend.entity.User.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByProviderAndUid(Provider provider, String uid);
}