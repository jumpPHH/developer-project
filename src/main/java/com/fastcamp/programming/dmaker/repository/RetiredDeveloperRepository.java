package com.fastcamp.programming.dmaker.repository;

import com.fastcamp.programming.dmaker.code.StatusCode;
import com.fastcamp.programming.dmaker.entity.Developer;
import com.fastcamp.programming.dmaker.entity.RetiredDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RetiredDeveloperRepository
        extends JpaRepository<RetiredDeveloper, Long> {
    Optional<RetiredDeveloper> findByMemberId(String memberId);
}
