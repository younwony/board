package wony.dev.board.board.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import wony.dev.board.board.model.Board;
import wony.dev.board.board.model.BoardDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Rollback
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    void setUp(){
        boardRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 등록")
    void register(){
        // given
        BoardDto boardDto = BoardDto.builder()
                .title("title test")
                .content("content test")
                .build();

        Board board = boardDto.toEntity();
        // when
        Board saveBoard = boardRepository.save(board);

        // then
        assertThat(board.getTitle()).isEqualTo(saveBoard.getTitle());
        assertThat(board.getContent()).isEqualTo(saveBoard.getContent());

        System.out.println(saveBoard);
    }

    @Test
    @DisplayName("게시글 ID로 찾기")
    void findById(){
        // given
        BoardDto boardDto = BoardDto.builder()
                .title("title test")
                .content("content test")
                .build();
        Board board = boardDto.toEntity();
        Board saveBoard = boardRepository.save(board);
        Long saveBoardId = saveBoard.getId();

        // when
        Board findBoard = boardRepository.findById(saveBoardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + saveBoardId));
        // then
        assertThat(findBoard.getId()).isEqualTo(saveBoardId);
    }

    @Test
    @DisplayName("게시글 전부 가져오기")
    void findAll(){
        // given
        // when
        List<Board> boards = boardRepository.findAll();
        // then
        for (Board board : boards) {
            System.out.println(board);
        }
    }

    @Test
    @DisplayName("게시글 수정하기")
    void mod(){
        // given
        BoardDto boardDto = BoardDto.builder()
                .title("title Update")
                .content("content Update")
                .build();
        Board saveBoard = boardRepository.save(boardDto.toEntity());
        Long id = saveBoard.getId();

        Board findBoard = boardRepository.findById(id).get();
        boardDto.setContent("content Update2");
        boardDto.setTitle("title Update2");
        boardDto.toEntity();

        // when
        findBoard.update(boardDto);

        // then
        Board updateBoard = boardRepository.findById(id).get();
        assertThat(findBoard.getTitle()).isEqualTo(updateBoard.getTitle());
        assertThat(findBoard.getContent()).isEqualTo(updateBoard.getContent());

    }

    @Test
    @DisplayName("게시글 ID로 삭제하기")
    void deleteById(){
        // given
        BoardDto boardDto = BoardDto.builder()
                .title("title test")
                .content("content test")
                .build();
        Board board = boardDto.toEntity();
        Board saveBoard = boardRepository.save(board);
        Long id = saveBoard.getId();

        // when
        boardRepository.deleteById(id);

        // then
        assertThat(boardRepository.findById(id).isEmpty()).isTrue();
    }
}