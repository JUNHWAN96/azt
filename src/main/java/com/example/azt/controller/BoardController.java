package com.example.azt.controller;

import com.example.azt.dto.BoardDto;
import com.example.azt.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/write")
    public String writeBoardForm(){

        return "/board/write";
    };

    @PostMapping("/write")
    public String writeBoard(BoardDto boardDto){

        boardService.saveBoard(boardDto);

        return "redirect:/main/test";
    }

}
