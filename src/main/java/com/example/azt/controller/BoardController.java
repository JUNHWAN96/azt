package com.example.azt.controller;

import com.example.azt.dto.BoardDto;
import com.example.azt.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Path;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/write")
    public String writeBoardForm() {
        return "/board/write";
    }

    @PostMapping("/write")
    public String writeBoard(BoardDto boardDto) {

        boardService.saveBoard(boardDto);

        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String boardList(Model model) {

        List<BoardDto> boardDtoList = boardService.findAll();

        model.addAttribute("boardDtoList",boardDtoList);

        return "/board/list";
    }

    @GetMapping("/detail/{id}")
    public String boardDetail(@PathVariable Long id, Model model){

       BoardDto board = boardService.detail(id);

       model.addAttribute("board", board);

        return "/board/detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){

        BoardDto board = boardService.detail(id);

        model.addAttribute("board", board);

        return "/board/update";
    }

    @PostMapping("/update")
    public String update( BoardDto boardDto){

        boardService.updateBoard(boardDto);

        return "redirect:/board/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        boardService.delete(id);

        return "redirect:/board/list";
    }

}

