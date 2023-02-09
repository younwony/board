package wony.dev.board.BulletinBoard.service;

import wony.dev.board.BulletinBoard.model.BoardDto;

import java.util.List;

public interface BoardService {
    BoardDto register(BoardDto boardDto);
    BoardDto findById(Long id);
    List<BoardDto> findAll();
    void update(Long id, BoardDto boardDto);
    void deleteById(Long id);
}
