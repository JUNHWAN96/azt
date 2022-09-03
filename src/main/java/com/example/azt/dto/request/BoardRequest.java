package com.example.azt.dto.request;

import com.example.azt.dto.BoardDto;
import com.example.azt.dto.UserAccountDto;

public class BoardRequest {

    private String title;
    private String hashtag;
    private String content;

    public BoardRequest(String title, String hashtag, String content) {
        this.title = title;
        this.hashtag = hashtag;
        this.content = content;
    }

    public static BoardRequest of(String title, String hashtag, String content){
        return new BoardRequest(title,hashtag,content);
    }

    public BoardDto toDto(UserAccountDto userAccountDto){
        return BoardDto.of(title,hashtag,content,userAccountDto);
    }

}
