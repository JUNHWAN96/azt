package com.example.azt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class BoardComment extends AuditingFields  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String content;

    @ManyToOne
    @JoinColumn(name = "boardid")
    private Board board;

    @ManyToOne
    @JoinColumn(name ="username")
    private UserAccount userAccount;

    protected BoardComment(){};

    @Builder
    public BoardComment(Long id, String content, Board board, UserAccount userAccount) {
        this.id = id;
        this.content = content;
        this.board = board;
        this.userAccount = userAccount;
    }
}
