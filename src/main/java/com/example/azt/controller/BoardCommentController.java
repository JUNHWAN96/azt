package com.example.azt.controller;

import com.example.azt.domain.constant.UserType;
import com.example.azt.dto.BoardCommentDto;
import com.example.azt.dto.UserAccountDto;
import com.example.azt.dto.request.BoardCommentRequest;
import com.example.azt.dto.security.BoardPrincipal;
import com.example.azt.service.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @PostMapping("/write")
    public String writeBoardComment(BoardCommentRequest boardCommentRequest, @AuthenticationPrincipal BoardPrincipal boardPrincipal){

        boardCommentService.saveBoardComment(boardCommentRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/board/detail/" + boardCommentRequest.getBoardId();

    };

    @PostMapping("{id}/delete")
    public String deleteBoardComment(@PathVariable Long id,
                                     @AuthenticationPrincipal BoardPrincipal boardPrincipal,
                                     Long boardId){

        boardCommentService.deleteBoardComment(id,boardPrincipal.getUsername());

        return "redirect:/board/detail/" + boardId;
    }

}
