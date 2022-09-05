package com.example.azt.controller;

import com.example.azt.domain.constant.SearchType;
import com.example.azt.dto.BoardCommentDto;
import com.example.azt.dto.BoardDto;
import com.example.azt.dto.request.BoardRequest;
import com.example.azt.dto.security.BoardPrincipal;
import com.example.azt.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public String writeBoard(BoardRequest boardRequest, @AuthenticationPrincipal BoardPrincipal boardPrincipal) {

        boardService.saveBoard(boardRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/board/list";
    }
    

    @GetMapping("/list")
    public String boardList(@RequestParam(required = false) SearchType searchType,
                            @RequestParam(required = false) String searchValue,
                            @PageableDefault(size=5, sort="id", direction= Sort.Direction.DESC)Pageable pageable,
                             Model model) {

        Page<BoardDto> boardDtoList = boardService.searchBoard(searchType,searchValue,pageable);

        int startPage = Math.max( 1,boardDtoList.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boardDtoList.getTotalPages(),boardDtoList.getPageable().getPageNumber() + 4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boardDtoList",boardDtoList);
        model.addAttribute("searchType", SearchType.values());

        return "/board/list";
    }

    @GetMapping("/detail/{id}")
    public String boardDetail(@PathVariable Long id, Model model){

       BoardDto board = boardService.detail(id);
       List< BoardCommentDto> boardCommentDtos = boardService.getComments(id);

       model.addAttribute("board", board);
       model.addAttribute("boardComments", boardCommentDtos);

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

