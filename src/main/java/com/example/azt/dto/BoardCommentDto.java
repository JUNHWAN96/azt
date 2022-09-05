package com.example.azt.dto;

import com.example.azt.domain.Board;
import com.example.azt.domain.BoardComment;
import com.example.azt.domain.UserAccount;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@Setter
@Getter
public class BoardCommentDto {

    private Long id;
    private Long boardId;
    private String content;
    private UserAccountDto userAccountDto;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public static BoardCommentDto of(Long boardId, UserAccountDto userAccountDto, String content){
        return new BoardCommentDto(null,content,boardId,userAccountDto,null,null);
    }

    public BoardCommentDto(Long id, String content, Long boardId, UserAccountDto userAccountDto,
                           LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.content = content;
        this.boardId = boardId;
        this.userAccountDto = userAccountDto;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public BoardComment toEntity(UserAccount userAccount, Board board){
        BoardComment boardComment = BoardComment.builder()
                                    .id(id)
                                    .content(content)
                                    .userAccount(userAccount)
                                    .board(board)
                                    .build();
        return boardComment;
    }

    public static BoardCommentDto fromEntity(BoardComment entity){
        return new BoardCommentDto(
                entity.getId(),
                entity.getContent(),
                entity.getBoard().getId(),
                UserAccountDto.fromEntity(entity.getUserAccount()),
                entity.getCreatedDate(),
                entity.getModifiedDate()
        );
    }
}
