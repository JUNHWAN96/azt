package com.example.azt.repository;

import com.example.azt.domain.BoardComment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

   List<BoardComment> findByBoardId(Long id);

   @Transactional
    void deleteByIdAndUserAccount_UserName(Long id, String username);
}
