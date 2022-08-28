package com.example.azt.repository;

import com.example.azt.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitleContaining(String keyword, Pageable pageable);
    Page<Board> findByContentContaining(String keyword, Pageable pageable);
    Page<Board> findByHashtagContaining(String keyword, Pageable pageable);
    Page<Board> findByWriterContaining(String keyword, Pageable pageable);

}
