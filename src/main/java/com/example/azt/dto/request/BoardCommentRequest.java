package com.example.azt.dto.request;

import com.example.azt.dto.BoardCommentDto;
import com.example.azt.dto.UserAccountDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardCommentRequest {

    private Long boardId;
    private String content;

    public static BoardCommentRequest of(Long boardId, String content){
        return new BoardCommentRequest(boardId,content);
    }

    public BoardCommentRequest(Long boardId, String content) {
        this.boardId = boardId;
        this.content = content;
    }

    public BoardCommentDto toDto(UserAccountDto userAccountDto){
        return BoardCommentDto.of(
                boardId,
                userAccountDto,
                content
        );
    }

}
