package com.daylog.backend.controller;

import com.daylog.backend.entity.User;
import com.daylog.backend.entity.User.Provider;
import com.daylog.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 임시: provider, uid를 파라미터로 받음 (추후 인증 필터로 대체)
    @GetMapping("/me")
    public User getMe(@RequestParam Provider provider, @RequestParam String uid) {
        return userService.getUserByProviderAndUid(provider, uid);
    }
}