package com.daylog.backend.repository;

import com.daylog.backend.entity.Memo;
import com.daylog.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByUserAndMemoDate(User user, LocalDate memoDate);

    List<Memo> findAllByUserAndMemoDateBetween(User user, LocalDate start, LocalDate end);
}