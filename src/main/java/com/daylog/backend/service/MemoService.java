package com.daylog.backend.service;

import com.daylog.backend.entity.Memo;
import com.daylog.backend.entity.User;
import com.daylog.backend.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    public Memo createMemo(Memo memo) {
        return memoRepository.save(memo);
    }

    public List<Memo> getMemosByDate(User user, LocalDate date) {
        return memoRepository.findAllByUserAndMemoDate(user, date);
    }

    public List<Memo> getMemosByWeek(User user, LocalDate start, LocalDate end) {
        return memoRepository.findAllByUserAndMemoDateBetween(user, start, end);
    }

    public Memo updateMemo(Memo memo) {
        return memoRepository.save(memo);
    }

    public void deleteMemo(Long id) {
        memoRepository.deleteById(id);
    }

    public Memo getMemoById(Long id) {
        return memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Memo not found"));
    }
}