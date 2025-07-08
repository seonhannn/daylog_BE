package com.daylog.backend.controller;

import com.daylog.backend.entity.User;
import com.daylog.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import com.daylog.backend.security.FirebaseAuthentication;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public User getMe() {
        FirebaseAuthentication auth = (FirebaseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        String provider = auth.getProvider();
        String uid = (String) auth.getPrincipal();
        return userService.getUserByProviderAndUid(User.Provider.valueOf(provider.toUpperCase()), uid);
    }

    @DeleteMapping("/me")
    public void deleteMe() {
        FirebaseAuthentication auth = (FirebaseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        String provider = auth.getProvider();
        String uid = (String) auth.getPrincipal();
        userService.deleteByProviderAndUid(User.Provider.valueOf(provider.toUpperCase()), uid);
    }
}