package com.example.azt.dto;

import com.example.azt.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BoardDto {

    private Long id;

    private String title;

    private String hashtag;
    private String content;
    private String writer;

    public BoardDto(Long id, String title, String hashtag ,String content, String writer) {
        this.id = id;
        this.title = title;
        this.hashtag = hashtag;
        this.content = content;
        this.writer = writer;
    }

    public Board toEntity(){
        Board board = Board.builder()
                .id(id)
                .title(title)
                .hashtag(hashtag)
                .content(content)
                .writer(writer)
                .build();
        return board;
    }

    public static BoardDto fromEntity(Board entity){
        return new BoardDto(
                entity.getId(),
                entity.getTitle(),
                entity.getHashtag(),
                entity.getContent(),
                entity.getWriter()
        );
    }
}
