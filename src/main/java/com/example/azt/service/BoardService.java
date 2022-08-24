package com.example.azt.service;

import com.example.azt.dto.BoardDto;
import com.example.azt.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public void saveBoard(BoardDto boardDto){
        boardRepository.save(boardDto.toEntity());
    }

}
