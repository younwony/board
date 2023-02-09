package wony.dev.board.BulletinBoard.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import wony.dev.board.BulletinBoard.model.Board;
import wony.dev.board.BulletinBoard.model.BoardDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Rollback
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

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
        Long id = 101l;
        // when
        Board findBoard = boardRepository.findById(id)
                .orElseGet(Board::new);
        // then
        assertThat(findBoard.getId()).isEqualTo(id);
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
        long id = 101l;
        BoardDto boardDto = BoardDto.builder()
                .title("title Update")
                .content("content Update")
                .build();

        Board board = boardRepository.findById(id).get();
        // when
        board.update(boardDto);

        // then
        Board saveBoard = boardRepository.findById(id).get();
        assertThat(board.getTitle()).isEqualTo(saveBoard.getTitle());
        assertThat(board.getContent()).isEqualTo(saveBoard.getContent());
    }

    @Test
    @DisplayName("게시글 ID로 삭제하기")
    void deleteById(){
        // given
        Long id = 101l;
        // when
        boardRepository.deleteById(id);
        // then
        assertThat(boardRepository.findById(id).isEmpty()).isTrue();
    }
}