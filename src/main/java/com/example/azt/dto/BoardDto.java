package com.example.azt.dto;

import com.example.azt.domain.Board;
import com.example.azt.domain.UserAccount;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class BoardDto {

    private Long id;

    private String title;

    private String hashtag;
    private String content;
    private String writer;

    private UserAccount userAccount;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardDto(Long id, String title, String hashtag ,String content, String writer,UserAccount userAccount ,LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.hashtag = hashtag;
        this.content = content;
        this.writer = writer;
        this.userAccount = userAccount;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Board toEntity(){
        Board board = Board.builder()
                .id(id)
                .title(title)
                .hashtag(hashtag)
                .content(content)
                .writer(writer)
                .userAccount(userAccount)
                .build();
        return board;
    }

    public static BoardDto fromEntity(Board entity){
        return new BoardDto(
                entity.getId(),
                entity.getTitle(),
                entity.getHashtag(),
                entity.getContent(),
                entity.getWriter(),
                entity.getUserAccount(),
                entity.getCreatedDate(),
                entity.getModifiedDate()
        );
    }
}
