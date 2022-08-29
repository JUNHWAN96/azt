package com.example.azt.service;

import com.example.azt.domain.Board;
import com.example.azt.domain.UserAccount;
import com.example.azt.dto.BoardCommentDto;
import com.example.azt.repository.BoardCommentRepository;
import com.example.azt.repository.BoardRepository;
import com.example.azt.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final UserAccountRepository userAccountRepository;

    public void saveBoardComment(BoardCommentDto boardCommentDto){

        Board board = boardRepository.getReferenceById(boardCommentDto.getBoardId());
        UserAccount userAccount = userAccountRepository.getReferenceById(boardCommentDto.getUserAccountDto().getUsername());
        boardCommentRepository.save(boardCommentDto.toEntity(userAccount,board));

    }

}
