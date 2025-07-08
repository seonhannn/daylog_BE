package com.daylog.backend.controller;

import com.daylog.backend.entity.Memo;
import com.daylog.backend.entity.User;
import com.daylog.backend.service.MemoService;
import com.daylog.backend.service.UserService;
import com.daylog.backend.security.FirebaseAuthentication;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/memos")
public class MemoController {
    private final MemoService memoService;
    private final UserService userService;

    public MemoController(MemoService memoService, UserService userService) {
        this.memoService = memoService;
        this.userService = userService;
    }

    @PostMapping
    public Memo createMemo(@RequestBody Memo memo) {
        FirebaseAuthentication auth = (FirebaseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        String provider = auth.getProvider();
        String uid = (String) auth.getPrincipal();
        User user = userService.getOrCreateUser(User.Provider.valueOf(provider.toUpperCase()), uid);
        memo.setUser(user);
        return memoService.createMemo(memo);
    }

    @GetMapping
    public List<Memo> getMemosByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        FirebaseAuthentication auth = (FirebaseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        String provider = auth.getProvider();
        String uid = (String) auth.getPrincipal();
        User user = userService.getUserByProviderAndUid(User.Provider.valueOf(provider.toUpperCase()), uid);
        return memoService.getMemosByDate(user, date);
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);
    }

    @PatchMapping("/{id}/type")
    public Memo updateMemoType(@PathVariable Long id, @RequestParam Memo.MemoType type) {
        Memo memo = memoService.getMemoById(id);
        memo.setType(type);
        return memoService.updateMemo(memo);
    }
}