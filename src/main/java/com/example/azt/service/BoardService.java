package com.example.azt.service;

import com.example.azt.domain.Board;
import com.example.azt.dto.BoardDto;
import com.example.azt.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시물 저장
    public void saveBoard(BoardDto boardDto){
        boardRepository.save(boardDto.toEntity());
    }

    // 게시판 조회
    public List<BoardDto> findAll() {

        List<Board> boards = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boards ){
          BoardDto dto = BoardDto.builder()
                       .id(board.getId())
                       .title(board.getTitle())
                       .hashtag(board.getHashtag())
                       .content(board.getContent())
                       .writer(board.getWriter())
                       .build();

          boardDtoList.add(dto);
        }
        return boardDtoList;
    }

    public BoardDto detail(Long id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("게시글이 없습니다"));

        BoardDto dto = BoardDto.builder()
                      .id(board.getId())
                      .title(board.getTitle())
                      .hashtag(board.getHashtag())
                      .content(board.getContent())
                      .writer(board.getWriter())
                      .build();

        return dto;
    }

    public void updateBoard(BoardDto boardDto) {

            Board board = boardRepository.getReferenceById(boardDto.getId());

            board.setTitle(boardDto.getTitle());
            board.setHashtag(boardDto.getHashtag());
            board.setContent(boardDto.getContent());

            boardRepository.save(board);

    }

    public void delete(Long id) {

        boardRepository.deleteById(id);

    }
}
