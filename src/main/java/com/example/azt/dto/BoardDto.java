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

    private UserAccountDto userAccountDto;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static BoardDto of(String title ,String hashtag, String content, UserAccountDto userAccountDto){
        return new BoardDto(null,title,hashtag,content,userAccountDto,null,null);
    }

    public BoardDto(Long id, String title, String hashtag ,String content, UserAccountDto userAccountDto ,LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.hashtag = hashtag;
        this.content = content;
        this.userAccountDto = userAccountDto;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Board toEntity(UserAccount userAccount){
        return Board.of(
                userAccount,
                id,
                title,
                hashtag,
                content);
    }

    public static BoardDto fromEntity(Board entity){
        return new BoardDto(
                entity.getId(),
                entity.getTitle(),
                entity.getHashtag(),
                entity.getContent(),
                UserAccountDto.fromEntity(entity.getUserAccount()),
                entity.getCreatedDate(),
                entity.getModifiedDate()
        );
    }
}
