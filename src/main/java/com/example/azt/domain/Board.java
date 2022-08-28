package com.example.azt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Board extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username")
    private UserAccount userAccount;

    @Column(nullable = false) private String title;

    @Column private String hashtag;
    @Column private String content;
    @Column private String writer;

    @OrderBy("createdDate DESC ")
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL) // 양방향 관계 주인 설정
    private List<BoardComment> boardComments = new ArrayList<>();


    protected Board(){};

    @Builder
    public Board(UserAccount userAccount,Long id, String title, String hashtag, String content, String writer) {
        this.userAccount = userAccount;
        this.id = id;
        this.title = title;
        this.hashtag = hashtag;
        this.content = content;
        this.writer = writer;
    }
}
