package com.example.azt.dto;

import com.example.azt.domain.Board;

public class BoardDto {

    private String title;
    private String content;
    private String writer;

    public BoardDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Board toEntity(){
        Board board = Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
        return board;
    }
}
