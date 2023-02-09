package wony.dev.board.BulletinBoard.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wony.dev.board.BulletinBoard.model.Board;
import wony.dev.board.BulletinBoard.model.BoardDto;
import wony.dev.board.BulletinBoard.repository.BoardRepository;
import wony.dev.board.BulletinBoard.service.BoardService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public BoardDto register(BoardDto boardDto) {
        return BoardDto.of(boardRepository.save(boardDto.toEntity()));
    }

    @Override
    @Transactional(readOnly = true)
    public BoardDto findById(Long id) {
        return BoardDto.of(boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다.")));
    }
    @Override
    @Transactional(readOnly = true)
    public List<BoardDto> findAll() {
        return boardRepository.findAll().stream()
                .map(BoardDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long id, BoardDto boardDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        board.update(boardDto);
    }

    @Override
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
