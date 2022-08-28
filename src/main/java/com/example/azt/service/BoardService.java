package com.example.azt.service;

import com.example.azt.domain.Board;
import com.example.azt.domain.constant.SearchType;
import com.example.azt.dto.BoardDto;
import com.example.azt.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시물 저장
    public void saveBoard(BoardDto boardDto){
        boardRepository.save(boardDto.toEntity());
    }

    // 게시판 조회

    public Page<BoardDto> searchBoard(SearchType searchType, String searchKeyword, Pageable pageable) {

        //page 객체로 list를 받아 오는걸로 수정 + 검색 기능 추가
    if(searchKeyword == null || searchKeyword.isBlank()) {
        return boardRepository.findAll(pageable).map(BoardDto::fromEntity);
    }

    return switch (searchType){
        case TITLE -> boardRepository.findByTitleContaining(searchKeyword,pageable).map(BoardDto::fromEntity);
        case CONTENT -> boardRepository.findByContentContaining(searchKeyword,pageable).map(BoardDto::fromEntity);
        case HASHTAG -> boardRepository.findByHashtagContaining(searchKeyword,pageable).map(BoardDto::fromEntity);
        case WRITER -> boardRepository.findByWriterContaining(searchKeyword,pageable).map(BoardDto::fromEntity);
    };
//        List<Board> boards = boardRepository.findAll();
//        List<BoardDto> boardDtoList = new ArrayList<>();
//
//        for(Board board : boards ){
//          BoardDto dto = BoardDto.builder()
//                       .id(board.getId())
//                       .title(board.getTitle())
//                       .hashtag(board.getHashtag())
//                       .content(board.getContent())
//                       .writer(board.getWriter())
//                       .build();
//
//          boardDtoList.add(dto);
//        }
//        return boardDtoList;
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


    // 게시글 수정

    public void updateBoard(BoardDto boardDto) {

            Board board = boardRepository.getReferenceById(boardDto.getId());

            board.setTitle(boardDto.getTitle());
            board.setHashtag(boardDto.getHashtag());
            board.setContent(boardDto.getContent());

            boardRepository.save(board);

    }


    // 게시글 삭제

    public void delete(Long id) {

        boardRepository.deleteById(id);

    }
}
