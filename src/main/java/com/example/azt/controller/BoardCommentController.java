package com.example.azt.controller;

import com.example.azt.domain.constant.UserType;
import com.example.azt.dto.BoardCommentDto;
import com.example.azt.dto.UserAccountDto;
import com.example.azt.dto.request.BoardCommentRequest;
import com.example.azt.service.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @PostMapping("/write")
    public String writeBoardComment(BoardCommentRequest boardCommentRequest){

        boardCommentService.saveBoardComment(boardCommentRequest.toDto(UserAccountDto.of(
                "junhwan","test","test@email.com","부산","숑숑비숑", UserType.USER)));

        return "redirect:/board/detail/" + boardCommentRequest.getBoardId();

    };

}
