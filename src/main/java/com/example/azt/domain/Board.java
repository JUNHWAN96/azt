package com.example.azt.domain;

import lombok.Builder;

import javax.persistence.*;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가
    @Column
    private Long id;

    @Column(nullable = false) private String title;
    @Column private String content;
    @Column private String writer;

    protected Board(){};

    @Builder
    public Board(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
