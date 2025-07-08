package com.daylog.backend.controller;

import com.daylog.backend.entity.Memo;
import com.daylog.backend.entity.User;
import com.daylog.backend.entity.User.Provider;
import com.daylog.backend.service.MemoService;
import com.daylog.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/memos")
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;
    private final UserService userService;

    // 임시: provider, uid를 파라미터로 받음 (추후 인증 필터로 대체)
    @PostMapping
    public Memo createMemo(@RequestParam Provider provider, @RequestParam String uid, @RequestBody Memo memo) {
        User user = userService.getOrCreateUser(provider, uid);
        memo.setUser(user);
        return memoService.createMemo(memo);
    }

    @GetMapping
    public List<Memo> getMemosByDate(@RequestParam Provider provider, @RequestParam String uid,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        User user = userService.getUserByProviderAndUid(provider, uid);
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