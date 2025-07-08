package com.daylog.backend.service;

import com.daylog.backend.entity.User;
import com.daylog.backend.entity.User.Provider;
import com.daylog.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getOrCreateUser(Provider provider, String uid) {
        return userRepository.findByProviderAndUid(provider, uid)
                .orElseGet(() -> userRepository.save(User.builder()
                        .id(provider + ":" + uid)
                        .provider(provider)
                        .uid(uid)
                        .build()));
    }

    public User getUserByProviderAndUid(Provider provider, String uid) {
        return userRepository.findByProviderAndUid(provider, uid)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public void deleteByProviderAndUid(Provider provider, String uid) {
        userRepository.findByProviderAndUid(provider, uid)
                .ifPresent(userRepository::delete);
    }
}